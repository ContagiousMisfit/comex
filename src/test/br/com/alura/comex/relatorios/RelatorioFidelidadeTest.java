package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.builder.PedidoBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class RelatorioFidelidadeTest {

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

        List<Pedido> listaDePedidos = new ArrayList<>();
        listaDePedidos.add(primeiroPedido);
        listaDePedidos.add(segundoPedido);
        listaDePedidos.add(terceiroPedido);

        Consumer consumer = Mockito.mock(Consumer.class);

        RelatorioFidelidade relatorio = new RelatorioFidelidade(listaDePedidos, consumer);
        relatorio.executa();

        List<RelatorioFidelidade.ClientesFieis> resultado = relatorio.getClientesFieis();
        Assertions.assertEquals(1, resultado.size());

        RelatorioFidelidade.ClientesFieis clientesFieis = resultado.get(0);
        Assertions.assertEquals("Francisco", clientesFieis.getCliente());
        Assertions.assertEquals(3, clientesFieis.getNumPedidos());
    }

}