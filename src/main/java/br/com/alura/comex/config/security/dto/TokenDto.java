package br.com.alura.comex.config.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenDto {

    private String token;
    private String tipoAutenticacao;

}
