package com.ekwateur.testTechnique.dao.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProClient extends Client{

    @NotNull(message = "siret reference cannot be empty")
    @NotEmpty(message = "siret reference cannot be null")
    private String siret;
    @NotNull(message = "raison Social reference cannot be empty")
    @NotEmpty(message = "raison Social reference cannot be null")
    private String raisonSocial;
    private double ca;
}
