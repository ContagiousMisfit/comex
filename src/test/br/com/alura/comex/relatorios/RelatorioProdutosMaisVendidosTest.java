package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.builder.PedidoBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class RelatorioProdutosMaisVendidosTest {

    @Test
    public void deveGerarRelatorioComTresPedidos() throws Exception {

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
                .comQuantidade(1)
                .comData(LocalDate.of(2021, 10, 23))
                .build();

        Pedido terceiroPedido = new PedidoBuilder()
                .comCategoria("LIVROS")
                .comProduto("Anne de Green Gables")
                .comCliente("Teresa")
                .comValor("51.23")
                .comQuantidade(1)
                .comData(LocalDate.of(2021, 7, 20))
                .build();

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(primeiroPedido);
        pedidos.add(segundoPedido);
        pedidos.add(terceiroPedido);
        Consumer consumer = Mockito.mock(Consumer.class);

        RelatorioProdutosMaisVendidos relatorio = new RelatorioProdutosMaisVendidos(pedidos, consumer);
        relatorio.executa();

        List<RelatorioProdutosMaisVendidos.ProdutosMaisVendidos> resultado = relatorio.getProdutosMaisVendidos();

        assertThat(resultado)
                .hasSize(1)
                .extracting(RelatorioProdutosMaisVendidos.ProdutosMaisVendidos::getProduto,
                        RelatorioProdutosMaisVendidos.ProdutosMaisVendidos::getQuantidadeVendida)
                .containsExactly(
                        tuple("Anne de Green Gables", 2),
                        tuple("Livro da vida", 1));
    }

    @Test
    public void deveGerarRelatorioComUmPedido() throws Exception {

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