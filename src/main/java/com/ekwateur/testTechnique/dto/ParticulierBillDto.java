package com.ekwateur.testTechnique.dto;

import com.ekwateur.testTechnique.dao.models.Bill;
import com.ekwateur.testTechnique.dao.models.ParticulierClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ParticulierBillDto {
    private ParticulierClient particulierClient;
    private Bill bill;
}
