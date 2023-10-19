package com.ekwateur.testTechnique.controller;

import com.ekwateur.testTechnique.dao.models.Bill;
import com.ekwateur.testTechnique.dao.models.ParticulierClient;
import com.ekwateur.testTechnique.dao.models.ProClient;
import com.ekwateur.testTechnique.dto.ParticulierBillDto;
import com.ekwateur.testTechnique.dto.ProBillDTO;
import com.ekwateur.testTechnique.service.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping(value = "/pro")
    public ResponseEntity<Bill> addBillForPro(@RequestBody ProBillDTO proBillDTO) {
        ProClient proClient = proBillDTO.getProClient();
        Bill bill = proBillDTO.getBill();
        return ResponseEntity.ok(billService.addBillForPro(proClient, bill));
    }
    @PostMapping(value = "/particulier")
    public ResponseEntity<Bill> addBillForParticular(@RequestBody ParticulierBillDto particulierBillDto) {
        ParticulierClient particulierClient = particulierBillDto.getParticulierClient();
        Bill bill = particulierBillDto.getBill();
        billService.addBillForParticular(particulierClient, bill);
        return ResponseEntity.ok(billService.addBillForParticular(particulierClient, bill));
    }
}
