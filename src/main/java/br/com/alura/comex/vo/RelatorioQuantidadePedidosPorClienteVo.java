package br.com.alura.comex.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioQuantidadePedidosPorClienteVo {

    String nomeCliente;
    long quantidadePedidos;

}
