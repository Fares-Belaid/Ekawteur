package com.ekwateur.testTechnique.dao.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Enumerated(EnumType.STRING)
        private EnergyType energyType;
        @Min(value = 0)
        private double amount;
        @Min(value = 0)
        private double quantity;
        @ManyToOne
        @JoinColumn(name = "client_id")
        private Client client;
        @JsonFormat(pattern = "MM/dd/yyyy")
        private LocalDate date;
}
