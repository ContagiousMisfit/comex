package br.com.alura.comex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends AbstractEntity {

    private String nome;
    private String cpf;
    private String telefone;

    @Embedded
    private Endereco endereco;

    @OneToMany
    private List<Pedido> listaDePedidos;

}
