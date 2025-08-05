package com.example.AppCongeAb.repository;

import com.example.AppCongeAb.model.Agent;
import com.example.AppCongeAb.model.Conge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {

}
