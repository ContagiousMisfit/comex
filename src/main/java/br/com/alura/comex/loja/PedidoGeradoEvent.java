package br.com.alura.comex.loja;

import br.com.alura.comex.loja.api.model.DetalhesDoPedidoDto;
import br.com.alura.comex.loja.domain.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoGeradoEvent {

    private BigDecimal valorTotal;

    private Long idCliente;

    private String cpfCliente;

    private String nomeCliente;

    private Long pedidoId;

    private String endereco;

    private List<ItemDePedidoEvent> itemDePedidoEvents = new ArrayList<>();

    public PedidoGeradoEvent(Pedido pedido) {
        this.valorTotal = pedido.getValorTotal();
        this.idCliente = pedido.getCliente().getId();
        this.pedidoId = pedido.getId();
        this.nomeCliente = pedido.getCliente().getNome();
        this.itemDePedidoEvents = pedido.getItens().stream().map(ItemDePedidoEvent::new).toList();
    }

    public static List<DetalhesDoPedidoDto> converter(List<Pedido> pedidos) {
        return pedidos.stream().map(DetalhesDoPedidoDto::new).collect(Collectors.toList());
    }

}
