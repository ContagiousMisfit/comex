package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.processador.ProcessadorDeCSV;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TesteRelatorioProdutosMaisCaros {

    @Test
    public void deveGerarRelatorio() {

    }

    @Test
    public void recebeListaSemPedidosDeveLancarIllegalArgumentException() {
        List<Pedido> listaDePedidos = new ArrayList<>();
        assertTrue(listaDePedidos.isEmpty());
    }

    @BeforeEach
    public void inicializarCenario() {
        String arquivo = "pedidos.csv";
        ProcessadorDeCSV processadorDeCSV = new ProcessadorDeCSV(arquivo);
    }
}