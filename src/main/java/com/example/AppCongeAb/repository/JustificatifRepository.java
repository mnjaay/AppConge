package com.example.AppCongeAb.repository;

import com.example.AppCongeAb.model.Justificatif;
import com.example.AppCongeAb.model.Conge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JustificatifRepository extends JpaRepository<Justificatif, Long> {
    List<Justificatif> findByConge(Conge conge);
}
