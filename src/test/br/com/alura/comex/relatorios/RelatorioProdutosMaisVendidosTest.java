package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.builder.PedidoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

class RelatorioProdutosMaisVendidosTest {

    @Test
    public void deveGerarRelatorioComUmPedido() throws Exception {

        Pedido pedido = new PedidoBuilder()
                .comCategoria("LIVROS")
                .comProduto("Livro da vida")
                .comCliente("Teresa")
                .comValor("70.00")
                .comQuantidade(1)
                .comData(LocalDate.of(2022,5,30))
                .build();

        List<Pedido> pedidos = List.of(pedido);
        Consumer consumer = Mockito.mock(Consumer.class);

        RelatorioProdutosMaisVendidos relatorio = new RelatorioProdutosMaisVendidos(pedidos, consumer);
        relatorio.executa();

        List<RelatorioProdutosMaisVendidos.ProdutosMaisVendidos> resultado = relatorio.getProdutosMaisVendidos();
        Assertions.assertEquals(1, resultado.size());
        RelatorioProdutosMaisVendidos.ProdutosMaisVendidos primeiraVenda = resultado.get(0);

        Assertions.assertEquals("Livro da vida", primeiraVenda.getProduto());
        Assertions.assertEquals(1, primeiraVenda.getQuantidadeVendida());
    }
}