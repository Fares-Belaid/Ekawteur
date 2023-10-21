package com.ekwateur.testTechnique.controller;

import com.ekwateur.testTechnique.dao.models.Bill;
import com.ekwateur.testTechnique.dao.models.ParticulierClient;
import com.ekwateur.testTechnique.dao.models.ProClient;
import com.ekwateur.testTechnique.dto.ParticulierBillDto;
import com.ekwateur.testTechnique.dto.ProBillDTO;
import com.ekwateur.testTechnique.dto.SecondParticularBillDto;
import com.ekwateur.testTechnique.dto.SecondProBillDto;
import com.ekwateur.testTechnique.service.BillService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        return ResponseEntity.ok(billService.addBillForParticular(particulierClient, bill));
    }
    @GetMapping
    public ResponseEntity<Page<Bill>> getBill(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, String> search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Bill> bills = billService.getBills(pageable, search);
        return ResponseEntity.ok(bills);
    }

    // This is the second proposition where I supposed that the table in db contains the quantity of consumption and the
    //   frontend user should only choose by Month-Year
    @PostMapping(value = "/second-particulier")
    public ResponseEntity<Bill> addBillForParticular(@RequestBody SecondParticularBillDto secondParticularBillDto) {
        ParticulierClient particulierClient = secondParticularBillDto.getParticulierClient();
        return ResponseEntity.ok(billService.addBillForParticularSecondSolution(particulierClient, secondParticularBillDto.getDate()));
    }
    @PostMapping(value = "/second-pro")
    public ResponseEntity<Bill> addBillForPro(@RequestBody SecondProBillDto secondProBillDto) {
        ProClient proClient = secondProBillDto.getProClient();
        return ResponseEntity.ok(billService.addBillForProSecondSolution(proClient, secondProBillDto.getDate()));
    }
}
