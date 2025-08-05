package com.example.AppCongeAb.controller;

import com.example.AppCongeAb.model.Conge;
import com.example.AppCongeAb.service.Interface.IRHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rh")
public class RHController {

    @Autowired
    private IRHService rhService;

    @GetMapping("/demandes")
    public List<Conge> voirToutesLesDemandes() {
        return rhService.voirToutesLesDemandes();
    }

    @PostMapping("/valider/{id}")
    public void validerDemande(@PathVariable Long id) {
        rhService.validerDemande(id);
    }

    @PostMapping("/rejeter/{id}")
    public void rejeterDemande(@PathVariable Long id) {
        rhService.rejeterDemande(id);
    }

    @DeleteMapping("/annuler/{id}")
    public void annulerDemande(@PathVariable Long id) {
        rhService.annulerDemande(id);
    }
}
