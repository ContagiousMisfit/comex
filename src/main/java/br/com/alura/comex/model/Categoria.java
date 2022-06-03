package br.com.alura.comex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Categoria extends AbstractEntity {

    private String nome;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    private List<Produto> listaDeProdutos;

    public Categoria(String nome, Status status) {
        this.nome = nome;
        this.status = status;
    }
}
