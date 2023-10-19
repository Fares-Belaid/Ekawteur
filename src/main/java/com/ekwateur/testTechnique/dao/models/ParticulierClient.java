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
public class ParticulierClient extends Client{

    @NotNull(message = "civilite reference cannot be empty")
    @NotEmpty(message = "civilite reference cannot be null")
    private String civilite;
    @NotNull(message = "nom reference cannot be empty")
    @NotEmpty(message = "nom reference cannot be null")
    private String nom;
    @NotNull(message = "prenom reference cannot be empty")
    @NotEmpty(message = "prenom reference cannot be null")
    private String prenom;
}
