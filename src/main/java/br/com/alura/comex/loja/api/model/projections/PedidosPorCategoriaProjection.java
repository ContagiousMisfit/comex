package br.com.alura.comex.loja.api.model.projections;

import java.math.BigDecimal;

public interface PedidosPorCategoriaProjection {

    String getNomeCategoria();

    int getQuantidadeProdutosVendidos();

    BigDecimal getMontante();

}
