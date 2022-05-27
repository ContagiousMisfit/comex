package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.processador.ProcessadorDeCSV;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TesteRelatorioProdutosMaisVendidos {

    private ProcessadorDeCSV processadorDeCSV;

    @Test
    public void deveGerarRelatorio() {

    }

    @Test
    public void recebeListaComUmClienteDeveLancarIllegalArgumentException() throws Exception {
        String arquivo = "listaComUmClienteSo.csv";
        ProcessadorDeCSV processadorDeCSV = new ProcessadorDeCSV(arquivo);
        assertThrows(IllegalArgumentException.class, () -> processadorDeCSV.lerRegistros());
    }

}