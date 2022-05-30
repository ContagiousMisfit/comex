package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.builder.PedidoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class RelatorioProdutosMaisCarosTest {

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
        Assertions.assertEquals(1, resultado.size());
        RelatorioProdutosMaisCaros.ProdutosMaisCaros primeiraVenda = resultado.get(0);

        Assertions.assertEquals("ELETRÔNICOS", primeiraVenda.getCategoria());
        Assertions.assertEquals(new BigDecimal("4350.00"), primeiraVenda.getPreco());
        Assertions.assertEquals("Playstation 5", primeiraVenda.getProduto());
    }

    @Test
    public void recebeListaSemPedidosDeveLancarIllegalArgumentException() throws Exception {
        List<Pedido> pedidos = new ArrayList<>();
        Consumer consumer = Mockito.mock(Consumer.class);
        RelatorioVendasPorCategoria relatorio = new RelatorioVendasPorCategoria(pedidos, consumer);
        Assertions.assertThrows(IllegalArgumentException.class, () -> relatorio.executa());
    }
}