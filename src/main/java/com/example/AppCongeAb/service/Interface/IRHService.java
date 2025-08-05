package com.example.AppCongeAb.service.Interface;

import com.example.AppCongeAb.model.Conge;

import java.util.List;
import java.util.Optional;

public interface IRHService {
    void validerDemande(Long demandeId);
    void rejeterDemande(Long demandeId);
    void annulerDemande(Long demandeId);
    List<Conge> voirToutesLesDemandes();
    Optional<Conge> historiqueDemandesParAgent(Long agentId);
}