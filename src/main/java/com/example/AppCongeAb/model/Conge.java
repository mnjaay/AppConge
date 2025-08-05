package com.example.AppCongeAb.model;

import com.example.AppCongeAb.model.enume.StatutDemande;
import com.example.AppCongeAb.model.enume.TypeConge;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Conge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private TypeConge type;

    @Enumerated(EnumType.STRING)
    private StatutDemande statut;

    @ManyToOne
    @JsonBackReference
    private Agent agent;

    @OneToMany(mappedBy = "conge", cascade = CascadeType.ALL)
    private List<Justificatif> justificatifs;
}
