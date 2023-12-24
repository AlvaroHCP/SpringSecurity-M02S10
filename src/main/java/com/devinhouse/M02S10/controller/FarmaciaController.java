package com.devinhouse.M02S10.controller;

import com.devinhouse.M02S10.dto.FarmaciaRequest;
import com.devinhouse.M02S10.dto.FarmaciaResponse;
import com.devinhouse.M02S10.service.FarmaciaRepositoryService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmacias")
public class FarmaciaController {
    @Autowired
    FarmaciaRepositoryService farmaciaRepoService;

    @GetMapping
    public ResponseEntity<List<FarmaciaResponse>> farmaciasGet(){
        List<FarmaciaResponse> farmacias = farmaciaRepoService.GetAll();
        return ResponseEntity.ok(farmacias);
    };

    @GetMapping("/{cnpj}")
    public ResponseEntity<FarmaciaResponse> farmaciaByCNPJ(@PathVariable Long cnpj){
        FarmaciaResponse farmacia = farmaciaRepoService.Get(cnpj);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(farmacia);
    };

    @PostMapping
    @RolesAllowed( {"ADMIN","USUARIO"} )
    public ResponseEntity<FarmaciaResponse> inserirFarmacia(
            @RequestBody @Valid @NotNull FarmaciaRequest farmaciaRequest){
        Long cnpj = farmaciaRequest.getCnpj();
        farmaciaRepoService.cnpjAlreadyRegistered(cnpj);

        FarmaciaResponse farmaciaResponse = farmaciaRepoService.Save(farmaciaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(farmaciaResponse);
    }
}
