package com.devinhouse.M02S10.controller;

import com.devinhouse.M02S10.model.Usuario;
import com.devinhouse.M02S10.dto.UsuarioRequest;
import com.devinhouse.M02S10.dto.UsuarioResponse;
import com.devinhouse.M02S10.dto.security.AutenticacaoRequest;
import com.devinhouse.M02S10.dto.security.AutenticacaoResponse;
import com.devinhouse.M02S10.service.UsuarioService;
import com.devinhouse.M02S10.service.security.AutenticacaoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> consultar() {
        var usuarios = usuarioService.consultar();
        var resp = usuarios.stream().map(u -> mapper.map(u, UsuarioResponse.class)).toList();
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<UsuarioResponse> inserir(@RequestBody @Valid UsuarioRequest request) {
        var usuario = mapper.map(request, Usuario.class);
         usuario = usuarioService.inserir(usuario);
        var resp = mapper.map(usuario, UsuarioResponse.class);
        return ResponseEntity.created(URI.create(usuario.getId().toString())).body(resp);
    }

    @PutMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<UsuarioResponse> atualizar(@RequestBody @Valid UsuarioRequest request) {
        var usuario = mapper.map(request, Usuario.class);
        usuario = usuarioService.atualizar(usuario);
        var resp = mapper.map(usuario, UsuarioResponse.class);
        return ResponseEntity.created(URI.create(usuario.getId().toString())).body(resp);
    }

    @DeleteMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<HttpStatus> excluir(@RequestBody @Valid UsuarioRequest request) {
        var usuario = mapper.map(request, Usuario.class);
        usuarioService.excluir(usuario);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/autenticar")
    public ResponseEntity<AutenticacaoResponse> login(@RequestBody @Valid AutenticacaoRequest request) {
        var token = autenticacaoService.autenticar(request.getEmail(), request.getSenha());
        return ResponseEntity.ok(new AutenticacaoResponse(token));
    }

}