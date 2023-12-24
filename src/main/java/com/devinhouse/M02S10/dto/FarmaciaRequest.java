package com.devinhouse.M02S10.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FarmaciaRequest {

    @NotNull(message = "{required.fields}")
    private Long cnpj;
    @NotEmpty(message = "{required.fields}")
    private String razaoSocial;
    @NotEmpty(message = "{required.fields}")
    private String nomeFantasia;
    @NotEmpty(message = "{required.fields}")
    private String email;
    private String telefone;
    @NotEmpty(message = "{required.fields}")
    private String celular;
    @Valid
    private EnderecoRequest endereco;
}
