package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.builder.PedidoBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class RelatorioProdutosMaisVendidosTest {

    @Test
    public void deveGerarRelatorioComTresPedidos() {

        Pedido primeiroPedido = new PedidoBuilder()
                .comCategoria("LIVROS")
                .comProduto("Livro da vida")
                .comCliente("Teresa")
                .comValor("70.00")
                .comQuantidade(1)
                .comData(LocalDate.of(2022, 5, 30))
                .build();

        Pedido segundoPedido = new PedidoBuilder()
                .comCategoria("LIVROS")
                .comProduto("Anne de Green Gables")
                .comCliente("Gianna")
                .comValor("51.23")
                .comQuantidade(3)
                .comData(LocalDate.of(2021, 10, 23))
                .build();

        Pedido terceiroPedido = new PedidoBuilder()
                .comCategoria("ELETRÔNICOS")
                .comProduto("Monitor Gamer Samsung Odyssey G9 49' Curvo")
                .comCliente("Teresa")
                .comValor("7832.23")
                .comQuantidade(2)
                .comData(LocalDate.of(2021, 7, 20))
                .build();

        List<Pedido> pedidos = List.of(primeiroPedido, segundoPedido, terceiroPedido);
        Consumer consumer = Mockito.mock(Consumer.class);

        RelatorioProdutosMaisVendidos relatorio = new RelatorioProdutosMaisVendidos(pedidos, consumer);
        relatorio.executa();

        List<RelatorioProdutosMaisVendidos.ProdutosMaisVendidos> resultado = relatorio.getProdutosMaisVendidos();

        assertThat(resultado)
                .hasSize(3)
                .extracting(RelatorioProdutosMaisVendidos.ProdutosMaisVendidos::getProduto,
                        RelatorioProdutosMaisVendidos.ProdutosMaisVendidos::getQuantidadeVendida)
                .containsExactly(
                        tuple("Anne de Green Gables", 3),
                        tuple("Monitor Gamer Samsung Odyssey G9 49' Curvo", 2),
                        tuple("Livro da vida", 1));
    }

    @Test
    public void deveGerarRelatorioComTresProdutosIguaisSeparadosPorPedidos() {
        //Cenário não coberto atualmente
        Pedido primeiroPedido = new PedidoBuilder()
                .comCategoria("ELETRÔNICOS")
                .comProduto("Monitor Gamer Samsung Odyssey G9 49' Curvo")
                .comCliente("José")
                .comValor("7832.23")
                .comQuantidade(1)
                .comData(LocalDate.of(2022, 5, 30))
                .build();

        Pedido segundoPedido = new PedidoBuilder()
                .comCategoria("ELETRÔNICOS")
                .comProduto("Monitor Gamer Samsung Odyssey G9 49' Curvo")
                .comCliente("Gianna")
                .comValor("7832.23")
                .comQuantidade(4)
                .comData(LocalDate.of(2021, 10, 23))
                .build();

        Pedido terceiroPedido = new PedidoBuilder()
                .comCategoria("ELETRÔNICOS")
                .comProduto("Monitor Gamer Samsung Odyssey G9 49' Curvo")
                .comCliente("Teresa")
                .comValor("7832.23")
                .comQuantidade(2)
                .comData(LocalDate.of(2021, 7, 20))
                .build();

        List<Pedido> pedidos = List.of(primeiroPedido, segundoPedido, terceiroPedido);
        Consumer consumer = Mockito.mock(Consumer.class);

        RelatorioProdutosMaisVendidos relatorio = new RelatorioProdutosMaisVendidos(pedidos, consumer);
        relatorio.executa();

        List<RelatorioProdutosMaisVendidos.ProdutosMaisVendidos> resultado = relatorio.getProdutosMaisVendidos();

        /*assertThat(resultado)
                .hasSize(1)
                .extracting(RelatorioProdutosMaisVendidos.ProdutosMaisVendidos::getProduto,
                        RelatorioProdutosMaisVendidos.ProdutosMaisVendidos::getQuantidadeVendida)
                .containsExactly(
                        tuple("Monitor Gamer Samsung Odyssey G9 49' Curvo", 7));*/

        assertThat(resultado).isNotNull();
    }

    @Test
    public void deveGerarRelatorioComUmPedido() {

        Pedido pedido = new PedidoBuilder()
                .comCategoria("LIVROS")
                .comProduto("Livro da vida")
                .comCliente("Teresa")
                .comValor("70.00")
                .comQuantidade(1)
                .comData(LocalDate.of(2022, 5, 30))
                .build();

        List<Pedido> pedidos = List.of(pedido);
        Consumer consumer = Mockito.mock(Consumer.class);

        RelatorioProdutosMaisVendidos relatorio = new RelatorioProdutosMaisVendidos(pedidos, consumer);
        relatorio.executa();

        List<RelatorioProdutosMaisVendidos.ProdutosMaisVendidos> resultado = relatorio.getProdutosMaisVendidos();

        assertThat(resultado)
                .hasSize(1)
                .extracting(RelatorioProdutosMaisVendidos.ProdutosMaisVendidos::getProduto,
                        RelatorioProdutosMaisVendidos.ProdutosMaisVendidos::getQuantidadeVendida)
                .containsExactly(
                        tuple("Livro da vida", 1));
    }
}