package com.devinhouse.M02S10.service;

import com.devinhouse.M02S10.dto.FarmaciaRequest;
import com.devinhouse.M02S10.dto.FarmaciaResponse;
import com.devinhouse.M02S10.exception.AlreadyExistsException;
import com.devinhouse.M02S10.exception.NotFoundException;
import com.devinhouse.M02S10.model.Farmacia;
import com.devinhouse.M02S10.repository.FarmaciaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmaciaRepositoryService {

    @Autowired
    FarmaciaRepository farmaciaRepo;
    @Autowired
    ModelMapper mapper;

    public List<FarmaciaResponse> GetAll(){
        return farmaciaRepo.findAll().stream().map(item -> mapper.map(item, FarmaciaResponse.class)).toList();
    };
    public FarmaciaResponse Get(Long cnpj) {
        List<Farmacia> farmacias = farmaciaRepo.findAll()
                .stream().filter(item -> item.getCnpj().equals(cnpj)).toList();
        if(farmacias.isEmpty()) {
            throw new NotFoundException("Cnpj", cnpj.toString());
        }
        return mapper.map(farmacias.get(0), FarmaciaResponse.class);
    };

    public void cnpjAlreadyRegistered(Long cnpj){
        boolean farmacia = cnpjExists(cnpj);
        if(farmacia) {
            throw new AlreadyExistsException("Cnpj", cnpj.toString());
        }
    };

    public boolean cnpjExists(Long cnpj){
        return farmaciaRepo.existsById(cnpj);
    };

    public FarmaciaResponse Save(FarmaciaRequest farmacia){
        Farmacia response = farmaciaRepo.save(mapper.map(farmacia, Farmacia.class));
        return mapper.map(response, FarmaciaResponse.class);
    };

    public List<Farmacia> SaveAll(List<Farmacia> farmacias){
        return farmaciaRepo.saveAll(farmacias);
    };
}
