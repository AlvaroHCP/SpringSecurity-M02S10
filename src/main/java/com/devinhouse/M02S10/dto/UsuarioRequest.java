package com.devinhouse.M02S10.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsuarioRequest {

    @NotEmpty(message = "{required.field}")
    private String nome;

    @NotEmpty(message = "{required.field}")
    private String email;

    @NotEmpty(message = "{required.field}")
    private String senha;

    @NotEmpty(message = "{required.field}")
    private String role;

}
