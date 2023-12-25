package com.devinhouse.M02S10.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsuarioRequest {

    @NotEmpty(message = "{required.field}")
    private String name;

    @NotEmpty(message = "{required.field}")
    private String email;

    @NotEmpty(message = "{required.field}")
    private String password;

    @NotEmpty(message = "{required.field}")
    private String role;

}
