package com.example.AppCongeAb.service.Implement;

import com.example.AppCongeAb.model.Agent;
import com.example.AppCongeAb.model.Conge;
import com.example.AppCongeAb.model.Users;
import com.example.AppCongeAb.repository.CongeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CongeService {

    private final CongeRepository congeRepository;

    public CongeService(CongeRepository congeRepository) {
        this.congeRepository = congeRepository;
    }

    public Conge saveConge(Conge conge) {
        return congeRepository.save(conge);
    }

    public List<Conge> getCongesByUser(Agent agent) {
        return congeRepository.findByAgent(agent);
    }

    public Optional<Conge> getCongeById(Long id) {
        return congeRepository.findById(id);
    }

}
