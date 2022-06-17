package br.com.alura.comex.controller.dto.projections;

import java.math.BigDecimal;

public interface PedidosPorCategoriaProjection {

    String getNomeCategoria();

    int getQuantidadeProdutosVendidos();

    BigDecimal getMontante();

}
