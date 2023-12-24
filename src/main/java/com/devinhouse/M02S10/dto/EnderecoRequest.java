package com.devinhouse.M02S10.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoRequest {
    @NotNull(message = "{required.fields}")
    private Long cep;
    @NotEmpty(message = "{required.fields}")
    private String logradouro;
    @NotNull(message = "{required.fields}")
    private Integer numero;
    @NotEmpty(message = "{required.fields}")
    private String bairro;
    @NotEmpty(message = "{required.fields}")
    private String cidade;
    @NotEmpty(message = "{required.fields}")
    private String estado;
    private String complemento;
    @NotNull(message = "{required.fields}")
    private Double latitude;
    @NotNull(message = "{required.fields}")
    private Double longitude;
}
