package br.com.alura.comex.loja.api.security;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String gerarToken(Authentication authentication);

    boolean isTokenValid(String token);

    Long getIdUsuario(String token);

}
