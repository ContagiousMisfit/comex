package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.relatorios.utils.builder.PedidoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class RelatorioProdutosMaisCarosTest {

    @Test
    public void deveGerarRelatorioComTresPedidos() throws Exception {

        Pedido primeiroPedido = new PedidoBuilder()
                .comCategoria("DECORAÇÃO")
                .comProduto("Luminária de mesa cereijeira")
                .comCliente("Gianna")
                .comValor("200.00")
                .comQuantidade(2)
                .comData(LocalDate.of(2021, 8, 20))
                .build();

        Pedido segundoPedido = new PedidoBuilder()
                .comCategoria("VESTUÁRIO")
                .comProduto("Moletom Stranger Things")
                .comCliente("Francisco")
                .comValor("75.99")
                .comQuantidade(2)
                .comData(LocalDate.of(2022, 5, 30))
                .build();

        Pedido terceiroPedido = new PedidoBuilder()
                .comCategoria("DECORAÇÃO")
                .comProduto("Fita LED RGB 5050")
                .comCliente("Carlo")
                .comValor("37.90")
                .comQuantidade(1)
                .comData(LocalDate.of(2022, 5, 30))
                .build();

        List<Pedido> pedidos = List.of(primeiroPedido, segundoPedido, terceiroPedido);
        Consumer consumer = Mockito.mock(Consumer.class);
        RelatorioProdutosMaisCaros relatorio = new RelatorioProdutosMaisCaros(pedidos, consumer);
        relatorio.executa();

        List<RelatorioProdutosMaisCaros.ProdutosMaisCaros> resultado = relatorio.getProdutosMaisCaros();

        assertThat(resultado)
                .hasSize(2)
                .extracting(RelatorioProdutosMaisCaros.ProdutosMaisCaros::getCategoria,
                        RelatorioProdutosMaisCaros.ProdutosMaisCaros::getPreco,
                        RelatorioProdutosMaisCaros.ProdutosMaisCaros::getProduto)
                .containsExactly(
                        tuple("DECORAÇÃO", new BigDecimal("200.00"), "Luminária de mesa cereijeira"),
                        tuple("VESTUÁRIO", new BigDecimal("75.99"), "Moletom Stranger Things"));
    }

    @Test
    public void deveGerarRelatorioComUmPedido() throws Exception {

        Pedido pedido = new PedidoBuilder()
                .comCategoria("ELETRÔNICOS")
                .comProduto("Playstation 5")
                .comCliente("Gianna")
                .comValor("4350.00")
                .comQuantidade(3)
                .comData(LocalDate.of(2021, 8, 20))
                .build();

        List<Pedido> pedidos = List.of(pedido);
        Consumer consumer = Mockito.mock(Consumer.class);

        RelatorioProdutosMaisCaros relatorio = new RelatorioProdutosMaisCaros(pedidos, consumer);
        relatorio.executa();
        List<RelatorioProdutosMaisCaros.ProdutosMaisCaros> resultado = relatorio.getProdutosMaisCaros();

        assertThat(resultado)
                .hasSize(1)
                .extracting(RelatorioProdutosMaisCaros.ProdutosMaisCaros::getCategoria,
                        RelatorioProdutosMaisCaros.ProdutosMaisCaros::getPreco,
                        RelatorioProdutosMaisCaros.ProdutosMaisCaros::getProduto)
                .containsExactly(
                        tuple("ELETRÔNICOS", new BigDecimal("4350.00"), "Playstation 5"));

    }

    @Test
    public void recebeListaSemPedidosDeveLancarIllegalArgumentException() throws Exception {
        List<Pedido> pedidos = new ArrayList<>();
        Consumer consumer = Mockito.mock(Consumer.class);
        RelatorioVendasPorCategoria relatorio = new RelatorioVendasPorCategoria(pedidos, consumer);
        Assertions.assertThrows(IllegalArgumentException.class, () -> relatorio.executa());
    }
}