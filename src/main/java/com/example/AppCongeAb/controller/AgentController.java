package com.example.AppCongeAb.controller;

import com.example.AppCongeAb.model.Agent;
import com.example.AppCongeAb.model.Conge;
import com.example.AppCongeAb.model.enume.StatutDemande;
import com.example.AppCongeAb.repository.CongeRepository;
import com.example.AppCongeAb.service.Interface.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    @Autowired
    private IAgentService agentService;
    private CongeRepository CongeRepository;


    @PostMapping("/demander")
    public Conge demanderConge(@RequestBody Conge conge) {
        agentService.demanderConge(conge);
        return conge;
    }

    @DeleteMapping("/annuler/{id}")
    public void annulerDemande(@PathVariable Long id) {
        agentService.annulerDemande(id);
    }

    @GetMapping("/historique/{agentId}")
    public List<Conge> voirHistoriqueDemandes(@PathVariable Long agentId) {
        return agentService.voirHistoriqueDemandes(agentId);
    }

    @GetMapping("/solde/{agentId}")
    public int voirSoldeConge(@PathVariable Long agentId) {

        return agentService.voirSoldeConge(agentId);
    }

    @GetMapping("/agent/{agentId}/demande/{congeId}")
    public ResponseEntity<Conge> getDemandeByAgent(
            @PathVariable Long agentId,
            @PathVariable Long congeId) {

        Optional<Conge> conge = CongeRepository.findById(congeId);
        if (conge.isPresent() && conge.get().getAgent().getId().equals(agentId)) {
            return ResponseEntity.ok(conge.get());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Pas autorisé
        }
    }
    @PutMapping("/agent/{agentId}/demande/{congeId}")
    public ResponseEntity<?> modifierDemandeConge(
            @PathVariable Long agentId,
            @PathVariable Long congeId,
            @RequestBody Conge congeModifie) {

        Optional<Conge> congeOpt = CongeRepository.findById(congeId);

        if (congeOpt.isPresent()) {
            Conge conge = congeOpt.get();

            // Vérifier que c'est le bon agent
            if (!conge.getAgent().getId().equals(agentId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Pas autorisé à modifier.");
            }

            // Vérifier que la demande est encore modifiable
            if (conge.getStatut() != StatutDemande.EN_ATTENTE) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Impossible de modifier une demande validée ou rejetée.");
            }

            // Appliquer les modifications autorisées
            conge.setDateDebut(congeModifie.getDateDebut());
            conge.setDateFin(congeModifie.getDateFin());
            conge.setType(congeModifie.getType());
            // On peux autoriser d'autres champs ici

            CongeRepository.save(conge);
            return ResponseEntity.ok(conge);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
