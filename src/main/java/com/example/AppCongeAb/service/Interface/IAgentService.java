package com.example.AppCongeAb.service.Interface;

import com.example.AppCongeAb.model.Conge;
import com.example.AppCongeAb.model.Justificatif;
import com.example.AppCongeAb.model.Agent;

import java.util.List;

public interface IAgentService {
    void demanderConge(Conge conge);
    void annulerDemande(Long demandeId);
    List<Conge> voirHistoriqueDemandes(Long agentId);
    int voirSoldeConge(Long agentId);
    void ajouterJustificatif(Long demandeId, Justificatif justificatif);
}
