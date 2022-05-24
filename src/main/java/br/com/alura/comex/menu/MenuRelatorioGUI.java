package br.com.alura.comex.menu;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.processador.*;
import br.com.alura.comex.relatorios.*;

import javax.swing.*;
import java.util.List;

public class MenuRelatorioGUI {

    private List<Pedido> escolherArquivo() throws Exception {
        int opcao = pedirEscolhaProcessador();
        switch (opcao) {
            case 0:
                return ((Processador) new ProcessadorDeCSV()).lerRegistros();
            case 1:
                return ((Processador) new ProcessadorDeJSON()).lerRegistros();
            case 2:
                return ((Processador) new ProcessadorDeXML()).lerRegistros();
        }
        return escolherArquivo();
    }

    private void escolherRelatorio(List<Pedido> listaDePedidos) {
        int opcao = pedirEscolhaRelatorio();

        switch (opcao) {
            case 0:
                new RelatorioProxy(new RelatorioSintetico(listaDePedidos)).executa();
                break;
            case 1:
                new RelatorioProxy(new RelatorioFidelidade(listaDePedidos)).executa();
                break;
            case 2:
                new RelatorioProxy(new RelatorioVendasPorCategoria(listaDePedidos)).executa();
                break;
            case 3:
                new RelatorioProxy(new RelatorioProdutosMaisVendidos(listaDePedidos)).executa();
                break;
            case 4:
                new RelatorioProxy(new RelatorioProdutosMaisCaros(listaDePedidos)).executa();
                break;
            case 5:
                new RelatorioProxy(new RelatorioClientesMaisLucrativos(listaDePedidos)).executa();
                break;
            case 6:
                new RelatorioProxy(new RelatorioSintetico(listaDePedidos)).executa();
                new RelatorioProxy(new RelatorioFidelidade(listaDePedidos)).executa();
                new RelatorioProxy(new RelatorioVendasPorCategoria(listaDePedidos)).executa();
                new RelatorioProxy(new RelatorioProdutosMaisVendidos(listaDePedidos)).executa();
                new RelatorioProxy(new RelatorioProdutosMaisCaros(listaDePedidos)).executa();
                new RelatorioProxy(new RelatorioClientesMaisLucrativos(listaDePedidos)).executa();
                break;
        }
    }

    private int pedirEscolhaProcessador() {
        return JOptionPane.showOptionDialog(null, "BEM VINDO\n" +
                "Que tipo de arquivo deseja processar?", "PROCESSADOR", 0, JOptionPane.QUESTION_MESSAGE, null, CategoriaProcessador.values(), "CSV");
    }

    private int pedirEscolhaRelatorio() {
        return JOptionPane.showOptionDialog(null,
                "Qual relatório deseja imprimir?", "RELATÓRIO", 0, JOptionPane.QUESTION_MESSAGE, null, CategoriaRelatorio.values(), "RELATORIO_SINTETICO");
    }

    public void executar() throws Exception {
        List<Pedido> listaDePedidos = escolherArquivo();
        escolherRelatorio(listaDePedidos);
    }

}
