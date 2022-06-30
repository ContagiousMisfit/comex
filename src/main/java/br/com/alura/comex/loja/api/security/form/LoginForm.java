package br.com.alura.comex.loja.api.security.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface LoginForm {

    UsernamePasswordAuthenticationToken convert();

}
