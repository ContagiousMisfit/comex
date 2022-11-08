package br.com.alura.comex.loja.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Dimensoes {

    private Double comprimento;
    private Double altura;
    private Double largura;
    private Double peso;

}
