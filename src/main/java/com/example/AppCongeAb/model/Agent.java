package com.example.AppCongeAb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Agent extends Users {

    private int soldeConges;

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Conge> demandesConges;


}
