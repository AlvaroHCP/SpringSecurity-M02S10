package com.devinhouse.M02S10.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class ApiErrorResponse {
    private String title;
    private String message;
    private Map<String, String> details;
//    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timeStamp;

    public ApiErrorResponse(String titulo, String mensagem, Map<String, String> details) {
        this.title = titulo;
        this.message = mensagem;
        this.details = details;
        this.timeStamp = LocalDateTime.now();
    }
    public ApiErrorResponse(String titulo, String mensagem) {
        this(titulo, mensagem, null);
    }
    public ApiErrorResponse(String titulo) {
        this(titulo, null);
    }


}
