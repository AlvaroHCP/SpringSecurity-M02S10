package com.devinhouse.M02S10.service;

import com.devinhouse.M02S10.exception.AlreadyExistsException;
import com.devinhouse.M02S10.exception.NotFoundException;
import com.devinhouse.M02S10.model.Jornalista;
import com.devinhouse.M02S10.repository.JornalistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JornalistaService {

    @Autowired
    private JornalistaRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Jornalista inserir(Jornalista jornalista) {
        if (repo.existsByNome(jornalista.getNome()))
            throw new AlreadyExistsException("Usuario", jornalista.getNome());
        return repo.save(jornalista);
    }

    public Jornalista atualizar(Jornalista jornalista) {
        if (repo.existsByNome(jornalista.getNome())) {
            Jornalista antigo = repo.findByNome(jornalista.getNome()).get();

            antigo.setNome(jornalista.getNome());
            antigo.setSalario(jornalista.getSalario());
            antigo.setNivelDeCargo(jornalista.getNivelDeCargo());
            antigo.setNumeroDePublicacoes(jornalista.getNumeroDePublicacoes());

            return repo.save(antigo);
        }

        throw new AlreadyExistsException("Jornalista", jornalista.getNome());
    }

    public void excluir(Jornalista jornalista) {
        if (repo.existsByNome(jornalista.getNome())) {
            Jornalista antigo = repo.findByNome(jornalista.getNome()).get();
            repo.delete(antigo);
            return;
        }

        throw new NotFoundException("Jornalista", jornalista.getNome());
    }

    public List<Jornalista> consultar() {
        return repo.findAll();
    }

}
