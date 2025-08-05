package com.example.AppCongeAb.service.Implement;

import com.example.AppCongeAb.model.Conge;
import com.example.AppCongeAb.model.Justificatif;
import com.example.AppCongeAb.model.enume.StatutDemande;
import com.example.AppCongeAb.repository.CongeRepository;
import com.example.AppCongeAb.repository.JustificatifRepository;
import com.example.AppCongeAb.service.Interface.IAgentService;
import org.springframework.stereotype.Service;
import com.example.AppCongeAb.model.Agent;


import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@Service
public class AgentService implements IAgentService {

    private final CongeRepository congeRepository;
    private final JustificatifRepository justificatifRepository;

    public AgentService(CongeRepository congeRepository, JustificatifRepository justificatifRepository) {
        this.congeRepository = congeRepository;
        this.justificatifRepository = justificatifRepository;
    }

    @Override
    public void demanderConge(Conge conge) {
        conge.setStatut(StatutDemande.EN_ATTENTE);
        congeRepository.save(conge);
    }

    @Override
    public void annulerDemande(Long demandeId) {
        congeRepository.deleteById(demandeId);
    }

    @Override
    public List<Conge> voirHistoriqueDemandes(Long agentId) {
        return congeRepository.findByAgentId(agentId); // ou filtrer par user si nécessaire
    }

    @Override
    public int voirSoldeConge(Long agentId) {
        int totalJoursConges = 30; // Par exemple, 30 jours de congé par an

        // Récupère les congés validés de l'agent
        List<Conge> congesValides = congeRepository.findByAgentIdAndStatut(agentId, StatutDemande.VALIDEE);

        // Calcule le nombre total de jours pris
        int joursPris = congesValides.stream()
                .mapToInt(conge -> (int) ChronoUnit.DAYS.between(conge.getDateDebut(), conge.getDateFin()) + 1)
                .sum();

        return totalJoursConges - joursPris;
    }
    @Override
    public void ajouterJustificatif(Long demandeId, Justificatif justificatif) {
        Conge conge = congeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        justificatif.setConge(conge);
        justificatifRepository.save(justificatif);
    }



}
