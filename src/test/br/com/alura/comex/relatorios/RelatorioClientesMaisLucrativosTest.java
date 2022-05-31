package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.builder.PedidoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import java.util.List;
import java.util.function.Consumer;

class RelatorioClientesMaisLucrativosTest {

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

        RelatorioClientesMaisLucrativos relatorio = new RelatorioClientesMaisLucrativos(listaDePedidos, consumer);
        relatorio.executa();

        List<RelatorioClientesMaisLucrativos.ClientesMaisLucrativos> resultado = relatorio.getClientesMaisLucrativos();

        assertThat(resultado)
                .hasSize(1)
                .extracting(RelatorioClientesMaisLucrativos.ClientesMaisLucrativos::getNome,
                        RelatorioClientesMaisLucrativos.ClientesMaisLucrativos::getNumPedidos,
                        RelatorioClientesMaisLucrativos.ClientesMaisLucrativos::getMontanteGasto)
                .containsExactly(
                        tuple("Francisco", 3L, new BigDecimal("37303.89")));
    }

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
                .comCliente("Chiara")
                .comValor("7500.99")
                .comQuantidade(1)
                .comData(LocalDate.of(2020, 3, 11))
                .build();

        Pedido terceiroPedido = new PedidoBuilder()
                .comCategoria("ELETRÔNICOS")
                .comProduto("Macbook Pro 16")
                .comCliente("Maria")
                .comValor("31752.00")
                .comQuantidade(1)
                .comData(LocalDate.of(2022,10,23))
                .build();

        Pedido quartoPedido = new PedidoBuilder()
                .comCategoria("MÚSICA")
                .comProduto("Violoncelo")
                .comCliente("Maria")
                .comValor("750.00")
                .comQuantidade(1)
                .comData(LocalDate.of(2022,10,23))
                .build();

        List<Pedido> listaDePedidos = List.of(primeiroPedido, segundoPedido, terceiroPedido, quartoPedido);

        Consumer consumer = Mockito.mock(Consumer.class);

        RelatorioClientesMaisLucrativos relatorio = new RelatorioClientesMaisLucrativos(listaDePedidos, consumer);
        relatorio.executa();

        List<RelatorioClientesMaisLucrativos.ClientesMaisLucrativos> resultado = relatorio.getClientesMaisLucrativos();

        assertThat(resultado)
                .hasSize(2)
                .extracting(RelatorioClientesMaisLucrativos.ClientesMaisLucrativos::getNome,
                        RelatorioClientesMaisLucrativos.ClientesMaisLucrativos::getNumPedidos,
                        RelatorioClientesMaisLucrativos.ClientesMaisLucrativos::getMontanteGasto)
                .containsExactly(
                        tuple("Maria", 1L, new BigDecimal("32502")),
                        tuple("Chiara", 1L, new BigDecimal("7500.99")));
    }

}