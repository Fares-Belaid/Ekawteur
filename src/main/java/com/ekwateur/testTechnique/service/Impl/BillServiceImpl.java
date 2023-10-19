package com.ekwateur.testTechnique.service.Impl;

import com.ekwateur.testTechnique.dao.models.Bill;
import com.ekwateur.testTechnique.dao.models.Client;
import com.ekwateur.testTechnique.dao.models.ParticulierClient;
import com.ekwateur.testTechnique.dao.models.ProClient;
import com.ekwateur.testTechnique.dao.repository.BillRepository;
import com.ekwateur.testTechnique.dao.repository.ClientRepository;
import com.ekwateur.testTechnique.service.BillService;
import org.springframework.stereotype.Service;
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
        }else throw new IllegalArgumentException("Client not found");
    }

    @Override
    public Bill addBillForPro(ProClient proClient, Bill bill) {
       Client client = clientRepository.findByClientReference(proClient.getClientReference());
        if (client != null){
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
    }else throw new IllegalArgumentException("Client not found");
    }
}
