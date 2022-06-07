package br.com.alura.comex.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioMontantePorCategoriaVo {

    String nome;
    long quantidade;

}
