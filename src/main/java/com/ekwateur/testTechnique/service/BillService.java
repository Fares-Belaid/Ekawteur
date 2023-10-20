package com.ekwateur.testTechnique.service;

import com.ekwateur.testTechnique.dao.models.Bill;
import com.ekwateur.testTechnique.dao.models.ParticulierClient;
import com.ekwateur.testTechnique.dao.models.ProClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Map;

public interface BillService {
    Bill addBillForParticular(ParticulierClient particulierClient, Bill bill);
    Bill addBillForPro(ProClient proClient, Bill bill);
    Bill addBillForParticularSecondSolution(ParticulierClient particulierClient, LocalDate date);
    Bill addBillForProSecondSolution(ProClient proClient,  LocalDate date);
    Page<Bill> getBills(Pageable pageable, Map<String, String> search);

}
