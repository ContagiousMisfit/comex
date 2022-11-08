package br.com.alura.comex.loja.api.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenDto {

    private String token;
    private String tipoAutenticacao;

}
