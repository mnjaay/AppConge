package com.example.AppCongeAb.repository;

import com.example.AppCongeAb.model.Conge;
import com.example.AppCongeAb.model.Agent;
import com.example.AppCongeAb.model.enume.StatutDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongeRepository extends JpaRepository<Conge, Long> {

    List<Conge> findByAgent(Agent agent);

    List<Conge> findByAgentIdAndStatut(Long agentId, StatutDemande statut);

    List<Conge> findByStatut(StatutDemande statut);
    List<Conge> findByAgentId(Long agentId);

}
