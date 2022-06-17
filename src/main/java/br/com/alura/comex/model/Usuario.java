package br.com.alura.comex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private String email;
    private String senha;

    @OneToOne(mappedBy = "usuario")
    private Cliente cliente;

}
