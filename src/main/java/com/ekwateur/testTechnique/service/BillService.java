package com.ekwateur.testTechnique.service;

import com.ekwateur.testTechnique.dao.models.Bill;
import com.ekwateur.testTechnique.dao.models.ParticulierClient;
import com.ekwateur.testTechnique.dao.models.ProClient;

public interface BillService {
    Bill addBillForParticular(ParticulierClient particulierClient, Bill bill);
    Bill addBillForPro(ProClient proClient, Bill bill);
}
