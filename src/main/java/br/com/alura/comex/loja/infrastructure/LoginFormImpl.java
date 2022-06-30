package br.com.alura.comex.loja.infrastructure;

import br.com.alura.comex.loja.api.security.form.LoginForm;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class LoginFormImpl implements LoginForm {

    private String email;
    private String senha;

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }
}
