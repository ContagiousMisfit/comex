package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.processador.Processador;
import br.com.alura.comex.processador.ProcessadorDeCSV;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TesteRelatorioVendasPorCategoria {

    @Test
    public void deveGerarRelatorio() throws Exception {
        Processador processador = new ProcessadorDeCSV("pedidos.csv");
        List<Pedido> listaDePedidos = processador.lerRegistros();
        Relatorio relatorio = new RelatorioVendasPorCategoria(listaDePedidos);
        relatorio.executa();
    }

    @Test
    public void recebeListaVaziaDeveLancarIllegalArgumentException() throws Exception {
        ProcessadorDeCSV processadorDeCSV = new ProcessadorDeCSV(null);
        assertThrows(IllegalArgumentException.class, () -> processadorDeCSV.lerRegistros());
    }
}