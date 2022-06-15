package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Pedido;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class PedidoDto {

    private LocalDateTime dataPedido;

    private BigDecimal valorTotal;

    private BigDecimal desconto;

    private long quantidade;

    private Long idCliente;

    private String nomeCliente;

    public PedidoDto(Pedido pedido) {
        this.dataPedido = pedido.getData();
        this.valorTotal = pedido.getValorTotal();
        this.desconto = pedido.getDesconto();
        this.quantidade = pedido.getQuantidadeItens();
        this.idCliente = pedido.getCliente().getId();
        this.nomeCliente = pedido.getCliente().getNome();
    }

    public static Page<PedidoDto> converterPagina(Page<Pedido> pedidos) {
        return pedidos.map(PedidoDto::new);
    }

}
