package com.example.AppCongeAb.service.Implement;

import com.example.AppCongeAb.model.Conge;
import com.example.AppCongeAb.model.enume.StatutDemande;
import com.example.AppCongeAb.repository.CongeRepository;
import com.example.AppCongeAb.service.Interface.IRHService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RHService implements IRHService {

    private final CongeRepository congeRepository;

    public RHService(CongeRepository congeRepository) {
        this.congeRepository = congeRepository;
    }

    @Override
    public void validerDemande(Long demandeId) {
        Conge conge = congeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        conge.setStatut(StatutDemande.VALIDEE);
        congeRepository.save(conge);
    }

    @Override
    public void rejeterDemande(Long demandeId) {
        Conge conge = congeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        conge.setStatut(StatutDemande.REFUSE);
        congeRepository.save(conge);
    }

    @Override
    public void annulerDemande(Long demandeId) {
        congeRepository.deleteById(demandeId);
    }

    @Override
    public List<Conge> voirToutesLesDemandes() {

        return congeRepository.findByStatut(StatutDemande.EN_ATTENTE);
    }

    @Override
    public Optional<Conge> historiqueDemandesParAgent(Long agentId) {
        return congeRepository.findById(agentId);
    }

}
