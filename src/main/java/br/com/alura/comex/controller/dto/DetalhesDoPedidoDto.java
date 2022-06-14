package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.TipoDescontoPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DetalhesDoPedidoDto {

    private  LocalDateTime data;

    private BigDecimal valorTotal;

    private BigDecimal desconto;

    private List<ItemDePedidoDto> itemDePedidoDtos;

    private ClienteDto cliente;

    public DetalhesDoPedidoDto(Pedido pedido) {
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotal();
        this.desconto = pedido.getDesconto();
        this.itemDePedidoDtos = new ArrayList<>();
        this.itemDePedidoDtos.addAll(pedido.getItens().stream().map(ItemDePedidoDto::new).toList());
        this.cliente = new ClienteDto(pedido.getCliente());
    }

    public static List<DetalhesDoPedidoDto> converter(List<Pedido> pedidos) {
        return pedidos.stream().map(DetalhesDoPedidoDto::new).collect(Collectors.toList());
    }

}
