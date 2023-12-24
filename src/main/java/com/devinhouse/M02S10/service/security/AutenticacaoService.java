package com.devinhouse.M02S10.service.security;

import com.devinhouse.M02S10.exception.AuthenticationFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import com.devinhouse.M02S10.model.Usuario;

@Service
public class AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String autenticar(String email, String password) {
        try {
            // The authentication manager provides secure authentication and throws exception if it fails
            var authToken = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authenticate = authenticationManager.authenticate(authToken);
            var usuario  = (Usuario) authenticate.getPrincipal();
            String token = tokenService.gerarToken(usuario);
            return token;
        } catch (AuthenticationException e) {
            throw new AuthenticationFailException("Invalid User or Password");
        }
    }

}
