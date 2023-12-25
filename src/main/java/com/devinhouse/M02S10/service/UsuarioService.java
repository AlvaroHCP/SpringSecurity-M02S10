package com.devinhouse.M02S10.service;

import com.devinhouse.M02S10.exception.AlreadyExistsException;
import com.devinhouse.M02S10.exception.NotFoundException;
import com.devinhouse.M02S10.model.Usuario;
import com.devinhouse.M02S10.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Usuario inserir(Usuario usuario) {
        if (repo.existsByEmail(usuario.getEmail()))
            throw new AlreadyExistsException("Usuario", usuario.getEmail());
        String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);
        return repo.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
        if (repo.existsByEmail(usuario.getEmail())) {
            Usuario antigo = repo.findByEmail(usuario.getEmail()).get();
            String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(senhaCriptografada);

            antigo.setName(usuario.getName());
            antigo.setEmail(usuario.getEmail());
            antigo.setPassword(usuario.getPassword());
            antigo.setRole(usuario.getRole());

            return repo.save(antigo);
        }

        throw new AlreadyExistsException("Usuario", usuario.getEmail());
    }

    public void excluir(Usuario usuario) {
        if (repo.existsByEmail(usuario.getEmail())) {
            Usuario antigo = repo.findByEmail(usuario.getEmail()).get();
            repo.delete(antigo);
            return;
        }

        throw new NotFoundException("Usuario", usuario.getEmail());
    }

    public List<Usuario> consultar() {
        return repo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = repo.findByEmail(email);
        if (usuarioOpt.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return usuarioOpt.get();

        // mesma coisa
//        return repo.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}
