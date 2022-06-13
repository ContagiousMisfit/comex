package br.com.alura.comex.controller.form.cadastro;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.TipoDescontoPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoForm {

    private LocalDateTime data;

    private BigDecimal valorTotal = BigDecimal.ZERO;

    private Long idCliente;

    private BigDecimal desconto;

    private TipoDescontoPedido tipoDesconto;

    public Pedido converter() {
        return new Pedido();
    }

}
