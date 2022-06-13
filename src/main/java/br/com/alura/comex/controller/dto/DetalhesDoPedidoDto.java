package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.TipoDescontoPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DetalhesDoPedidoDto {

    private final Long id;

    private final LocalDateTime data;

    private BigDecimal valorTotal = BigDecimal.ZERO;

    private final ClienteDto cliente;

    private final BigDecimal desconto;

    private final TipoDescontoPedido tipoDesconto;

    public DetalhesDoPedidoDto(Pedido pedido) {
        this.id = pedido.getId();
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotal();
        this.cliente = new ClienteDto(pedido.getCliente());
        this.desconto = pedido.getDesconto();
        this.tipoDesconto = pedido.getTipoDesconto();

    }

    public static List<DetalhesDoPedidoDto> converter(List<Pedido> pedidos) {
        return pedidos.stream().map(DetalhesDoPedidoDto::new).collect(Collectors.toList());
    }

}
