package br.com.alura.comex.controller.form.cadastro;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@AllArgsConstructor
@ToString
@Data
public class ClienteForm {

    @NotNull
    @Size(min = 2)
    private String nome;

    @NotNull
    private String cpf;

    @NotNull
    private String telefone;

    @NotNull
    private String rua;

    @NotNull
    private String numero;

    private String complemento;

    @NotNull
    private String bairro;

    @NotNull
    private String cidade;

    @NotNull
    private String estado;

    public Cliente converter() {
        return new Cliente(nome, cpf, telefone, new Endereco(rua, numero, complemento, bairro, cidade, estado), new ArrayList<>());
    }

}

