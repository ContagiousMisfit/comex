package br.com.alura.comex.model.utils;

import br.com.alura.comex.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoBuilder {

    private LocalDateTime data;

    private Cliente cliente;

    private BigDecimal desconto;

    private TipoDescontoPedido tipoDesconto;

    private List<ItemDePedido> listaDePedidos;

    public PedidoBuilder comData(LocalDateTime data) {
        this.data = data;
        return this;
    }

    public PedidoBuilder comDataAtual() {
        this.data = LocalDateTime.now();
        return this;
    }

    public PedidoBuilder comCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public PedidoBuilder comDesconto(BigDecimal desconto) {
        this.desconto = desconto;
        return this;
    }

    public PedidoBuilder comTipoDesconto(TipoDescontoPedido tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
        return this;
    }

    public PedidoBuilder comListaDePedidos(List<ItemDePedido> listaDePedidos) {
        this.listaDePedidos = listaDePedidos;
        return this;
    }

    public PedidoBuilder aplicarDesconto() {
        if (cliente.getListaDePedidos().size() > 5) {
            this.tipoDesconto = TipoDescontoPedido.FIDELIDADE;
            this.desconto = new BigDecimal(0.5);
        }
        else {
            this.tipoDesconto = TipoDescontoPedido.NENHUM;
            this.desconto = BigDecimal.ZERO;
        }
        return this;
    }

    public Pedido build() {
        return new Pedido(data, cliente, desconto, tipoDesconto, listaDePedidos);
    }

}
