package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.builder.PedidoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import java.util.List;
import java.util.function.Consumer;

class RelatorioFidelidadeTest {

    @Test
    public void deveGerarRelatorioComTresPedidos() throws Exception {
        Pedido primeiroPedido = new PedidoBuilder()
                .comCategoria("DECORAÇÃO")
                .comProduto("Pintura Óleo sobre Tela")
                .comCliente("Francisco")
                .comValor("3050.90")
                .comQuantidade(1)
                .comData(LocalDate.of(2022,5,30))
                .build();

        Pedido segundoPedido = new PedidoBuilder()
                .comCategoria("MÚSICA")
                .comProduto("Violino Stradivarius")
                .comCliente("Francisco")
                .comValor("2500.99")
                .comQuantidade(1)
                .comData(LocalDate.of(2020, 3, 11))
                .build();

        Pedido terceiroPedido = new PedidoBuilder()
                .comCategoria("ELETRÔNICOS")
                .comProduto("Macbook Pro 16")
                .comCliente("Jacinta")
                .comValor("31752.00")
                .comQuantidade(1)
                .comData(LocalDate.of(2022,10,23))
                .build();

        List<Pedido> listaDePedidos = List.of(primeiroPedido, segundoPedido, terceiroPedido);

        Consumer consumer = Mockito.mock(Consumer.class);

        RelatorioFidelidade relatorio = new RelatorioFidelidade(listaDePedidos, consumer);
        relatorio.executa();

        List<RelatorioFidelidade.ClientesFieis> resultado = relatorio.getClientesFieis();

        assertThat(resultado)
                .hasSize(2)
                .extracting(RelatorioFidelidade.ClientesFieis::getCliente, RelatorioFidelidade.ClientesFieis::getNumPedidos)
                .containsExactly(
                        tuple("Francisco", 2L),
                        tuple("Jacinta", 1L));
    }

    @Test
    public void deveGerarRelatorioComVariosPedidosDoMesmoCliente() throws Exception {
        Pedido primeiroPedido = new PedidoBuilder()
                .comCategoria("DECORAÇÃO")
                .comProduto("Pintura Óleo sobre Tela")
                .comCliente("Francisco")
                .comValor("3050.90")
                .comQuantidade(1)
                .comData(LocalDate.of(2022,5,30))
                .build();

        Pedido segundoPedido = new PedidoBuilder()
                .comCategoria("MÚSICA")
                .comProduto("Violino Stradivarius")
                .comCliente("Francisco")
                .comValor("2500.99")
                .comQuantidade(1)
                .comData(LocalDate.of(2020, 3, 11))
                .build();

        Pedido terceiroPedido = new PedidoBuilder()
                .comCategoria("ELETRÔNICOS")
                .comProduto("Macbook Pro 16")
                .comCliente("Francisco")
                .comValor("31752.00")
                .comQuantidade(1)
                .comData(LocalDate.of(2022,10,23))
                .build();

        List<Pedido> listaDePedidos = List.of(primeiroPedido, segundoPedido, terceiroPedido);

        Consumer consumer = Mockito.mock(Consumer.class);

        RelatorioFidelidade relatorio = new RelatorioFidelidade(listaDePedidos, consumer);
        relatorio.executa();

        List<RelatorioFidelidade.ClientesFieis> resultado = relatorio.getClientesFieis();
        assertThat(resultado)
                .hasSize(1)
                .extracting(RelatorioFidelidade.ClientesFieis::getCliente, RelatorioFidelidade.ClientesFieis::getNumPedidos)
                .containsExactly(
                        tuple("Francisco", 3L));
    }

}