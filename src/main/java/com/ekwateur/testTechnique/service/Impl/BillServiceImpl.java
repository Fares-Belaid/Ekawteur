package com.ekwateur.testTechnique.service.Impl;

import com.ekwateur.testTechnique.dao.models.*;
import com.ekwateur.testTechnique.dao.repository.BillRepository;
import com.ekwateur.testTechnique.dao.repository.ClientRepository;
import com.ekwateur.testTechnique.service.BillService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final ClientRepository clientRepository;
    public BillServiceImpl(BillRepository billRepository, ClientRepository clientRepository) {
        this.billRepository = billRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Bill addBillForParticular(ParticulierClient particulierClient, Bill bill) {
        Client client = clientRepository.findByClientReference(particulierClient.getClientReference());
        if (client != null) {
            if (checkExisitngBill(client.getId(), bill.getDate().getMonthValue(), bill.getDate().getYear())){
                throw new IllegalArgumentException("ALready have a bill for that month and year");
            }else {
            double pricePerKwh;
            switch (bill.getEnergyType()) {
                case GAS:
                    pricePerKwh = 0.115;
                    break;
                case ELECTRICITY:
                    pricePerKwh = 0.121;
                    break;
                default:
                    throw new IllegalArgumentException("Wrong type of energy");
            }
            bill.setClient(client);
            bill.setAmount(bill.getQuantity() * pricePerKwh);
            return billRepository.save(bill);
            }
        }else throw new IllegalArgumentException("Client not found");
    }
    private Boolean checkExisitngBill(Long clientId, int month, int year){
        return billRepository.existsBillForClientOnMonthAndYear(clientId, month, year);
    }
    @Override
    public Bill addBillForPro(ProClient proClient, Bill bill) {
       Client client = clientRepository.findByClientReference(proClient.getClientReference());
        if (client != null){
            if (checkExisitngBill(client.getId(), bill.getDate().getMonthValue(), bill.getDate().getYear())){
                throw new IllegalArgumentException("ALready have a bill for that month and year");
            }else {
        double pricePerKwh = 0;
        if (proClient.getCa() >= 1000000) {
            switch (bill.getEnergyType()) {
                case GAS:
                    pricePerKwh = 0.111;
                    break;
                case ELECTRICITY:
                    pricePerKwh = 0.114;
                    break;
                default:
                    throw new IllegalArgumentException("Wrong type of energy");
            }
            bill.setClient(client);
            bill.setAmount(bill.getQuantity() * pricePerKwh);
            } else {
              if (proClient.getCa() < 1000000) {
                switch (bill.getEnergyType()) {
                    case GAS:
                        pricePerKwh = 0.118;
                        break;
                    case ELECTRICITY:
                        pricePerKwh = 0.113;
                        break;
                    default:
                        throw new IllegalArgumentException("Wrong type of energy");
                    }
                }
            bill.setClient(client);
            bill.setAmount(bill.getQuantity() * pricePerKwh);
            }
            return billRepository.save(bill);
            }
        }else throw new IllegalArgumentException("Client not found");
    }

    // In the second solution I supposed that the table in db contains the quantity of consumption and the
    // frontend user should only choose by Date
    @Override
    public Bill addBillForParticularSecondSolution(ParticulierClient particulierClient, LocalDate date) {
        Client client = clientRepository.findByClientReference(particulierClient.getClientReference());
        double pricePerKwh;
        if (client != null) {
            try {
                Bill bill = billRepository.findBillByClientAndDate(client.getId(), date);

                if (bill != null) {
                    switch (bill.getEnergyType()) {
                        case GAS:
                            pricePerKwh = 0.115;
                            break;
                        case ELECTRICITY:
                            pricePerKwh = 0.121;
                            break;
                        default:
                            throw new IllegalArgumentException("Wrong type of energy");
                    }
                    bill.setClient(client);
                    bill.setAmount(bill.getQuantity() * pricePerKwh);
                } else {
                    throw new IllegalArgumentException("No bill found for the given client and date");
                }
                return billRepository.save(bill);
            }catch (Exception e){
                throw new IllegalArgumentException(e.getMessage());
            }

        }else throw new IllegalArgumentException("Client not found");
    }

    //  the same logic for the pro client in second solution
    @Override
    public Bill addBillForProSecondSolution(ProClient proClient,  LocalDate date) {
        Client client = clientRepository.findByClientReference(proClient.getClientReference());
        if (client != null){
            double pricePerKwh = 0;
            Bill bill = billRepository.findBillByClientAndDate(client.getId(), date);
            if (bill != null){
                if (proClient.getCa() >= 1000000) {
                    switch (bill.getEnergyType()) {
                        case GAS:
                            pricePerKwh = 0.111;
                            break;
                        case ELECTRICITY:
                            pricePerKwh = 0.114;
                            break;
                        default:
                            throw new IllegalArgumentException("Wrong type of energy");
                    }
                    bill.setClient(client);
                    bill.setAmount(bill.getQuantity() * pricePerKwh);
                } else {
                    if (proClient.getCa() < 1000000) {
                        switch (bill.getEnergyType()) {
                            case GAS:
                                pricePerKwh = 0.118;
                                break;
                            case ELECTRICITY:
                                pricePerKwh = 0.113;
                                break;
                            default:
                                throw new IllegalArgumentException("Wrong type of energy");
                        }
                    }
                    bill.setClient(client);
                    bill.setAmount(bill.getQuantity() * pricePerKwh);
                }
            }else throw new IllegalArgumentException("No bill found for the given client and date");

            return billRepository.save(bill);
        }else throw new IllegalArgumentException("Client not found");
    }

    @Override
    public Page<Bill> getBills(Pageable pageable, Map<String, String> search) {
        BooleanBuilder predicateBuilder = new BooleanBuilder();

        if (search != null && !search.isEmpty()) {
            QBill qbill = QBill.bill;
            for (Map.Entry<String, String> entry : search.entrySet()) {
                String field = entry.getKey();
                String value = entry.getValue();
                if (value != null && !value.isEmpty()) {
                    switch (field) {
                        case "id":
                            predicateBuilder.and(qbill.id.eq(Long.valueOf(value)));
                            break;
                        case "clientId":
                            predicateBuilder.and(qbill.client.id.eq(Long.valueOf(value)));
                            break;
                        case "energyType":
                            predicateBuilder.and(qbill.energyType.eq(EnergyType.valueOf(value)));
                            break;
                        case "amount":
                            predicateBuilder.and(qbill.amount.eq(Double.valueOf(value)));
                            break;
                        case "quantity":
                            predicateBuilder.and(qbill.quantity.eq(Double.valueOf(value)));
                            break;
                        case "date":
                            predicateBuilder.and(qbill.date.eq(LocalDate.parse(value, DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
                            break;
                    }
                }
            }
        }

        Page<Bill> bills = billRepository.findAll(predicateBuilder, pageable);
        return bills;
    }
}
