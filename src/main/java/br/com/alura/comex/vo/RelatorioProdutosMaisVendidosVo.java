package br.com.alura.comex.vo;

import br.com.alura.comex.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioProdutosMaisVendidosVo {

    private List<Produto> produtosMaisVendidos = new ArrayList<>();

}
