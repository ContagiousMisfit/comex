package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.utils.PedidoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RelatorioProdutosMaisCarosTest {

    @Test
    public void deveGerarRelatorio() {

    }

    public void deveGerarRelatorioComUmPedido() throws Exception {

        Pedido pedido = new PedidoBuilder()
                .comCategoria("LIVROS")
                .comProduto("O guia do mochileiro das galáxias")
                .comCliente("Alexandre")
                .comValor("44.90")
                .comQuantidade(1)
                .comData(LocalDate.of(2022,5,30))
                .build();

        List<Pedido> pedidos = List.of(pedido);
        Consumer consumer = Mockito.mock(Consumer.class);

        RelatorioVendasPorCategoria relatorio = new RelatorioVendasPorCategoria(pedidos, consumer);
        relatorio.executa();

        List<RelatorioVendasPorCategoria.VendasPorCategoria> resultado = relatorio.getVendasPorCategoria();
        Assertions.assertEquals(1, resultado.size());
        RelatorioVendasPorCategoria.VendasPorCategoria primeiraVenda = resultado.get(0);

        Assertions.assertEquals("LIVROS", primeiraVenda.getCategoria());
        Assertions.assertEquals(1, primeiraVenda.getQuantidadeVendida());
    }

    @Test
    public void recebeListaVaziaDeveLancarIllegalArgumentException() throws Exception {
        List<Pedido> pedidos = null;
        Consumer consumer = Mockito.mock(Consumer.class);
        RelatorioVendasPorCategoria relatorio = new RelatorioVendasPorCategoria(pedidos, consumer);
        Assertions.assertThrows(IllegalArgumentException.class, () -> relatorio.executa());
    }
}