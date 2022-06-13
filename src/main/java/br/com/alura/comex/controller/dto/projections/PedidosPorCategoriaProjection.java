package br.com.alura.comex.controller.dto.projections;

import java.math.BigDecimal;

public interface PedidosPorCategoriaProjection {

    String getCategoria();
    long getQuantidadeProdutos();
    BigDecimal getMontante();

}
