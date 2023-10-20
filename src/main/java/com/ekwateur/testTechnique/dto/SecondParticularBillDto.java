package com.ekwateur.testTechnique.dto;

import com.ekwateur.testTechnique.dao.models.ParticulierClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SecondParticularBillDto {
    private ParticulierClient particulierClient;
    private LocalDate date;
}
