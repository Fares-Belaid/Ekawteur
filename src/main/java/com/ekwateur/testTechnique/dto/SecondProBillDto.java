package com.ekwateur.testTechnique.dto;

import com.ekwateur.testTechnique.dao.models.ProClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SecondProBillDto {

    private ProClient proClient;
    private LocalDate date;
}
