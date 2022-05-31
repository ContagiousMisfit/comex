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
                return ((Processador) new ProcessadorDeCSV("pedidos.csv")).lerRegistros();
            case 1:
                return ((Processador) new ProcessadorDeJSON("pedidos.json")).lerRegistros();
            case 2:
                return ((Processador) new ProcessadorDeXML("pedidos.xml")).lerRegistros();
        }
        return escolherArquivo();
    }

    private void escolherRelatorio(List<Pedido> listaDePedidos) {
        int opcao = pedirEscolhaRelatorio();

        switch (opcao) {
            case 0:
                new RelatorioProxy(new RelatorioSintetico(listaDePedidos, relatorio -> {
                    JOptionPane.showMessageDialog(null, relatorio);
                })).executa();
                break;
            case 1:
                new RelatorioProxy(new RelatorioFidelidade(listaDePedidos, relatorio -> {
                    JOptionPane.showMessageDialog(null, relatorio);
                })).executa();
                break;
            case 2:
                new RelatorioProxy(new RelatorioVendasPorCategoria(listaDePedidos, relatorio -> {
                    JOptionPane.showMessageDialog(null, relatorio);
                })).executa();
                break;
            case 3:
                new RelatorioProxy(new RelatorioProdutosMaisVendidos(listaDePedidos, relatorio -> {
                    JOptionPane.showMessageDialog(null, relatorio);
                })).executa();
                break;
            case 4:
                new RelatorioProxy(new RelatorioProdutosMaisCaros(listaDePedidos, relatorio -> {
                    JOptionPane.showMessageDialog(null, relatorio);
                })).executa();
                break;
            case 5:
                new RelatorioProxy(new RelatorioClientesMaisLucrativos(listaDePedidos, relatorio -> {
                    JOptionPane.showMessageDialog(null, relatorio);
                })).executa();
                break;
            case 6:
                new RelatorioProxy(new RelatorioSintetico(listaDePedidos, relatorio -> {
                    JOptionPane.showMessageDialog(null, relatorio);
                })).executa();
                new RelatorioProxy(new RelatorioFidelidade(listaDePedidos, relatorio -> {
                    JOptionPane.showMessageDialog(null, relatorio);
                })).executa();
                new RelatorioProxy(new RelatorioVendasPorCategoria(listaDePedidos, relatorio -> {
                    JOptionPane.showMessageDialog(null, relatorio);
                })).executa();
                new RelatorioProxy(new RelatorioProdutosMaisVendidos(listaDePedidos, relatorio -> {
                    JOptionPane.showMessageDialog(null, relatorio);
                })).executa();
                new RelatorioProxy(new RelatorioProdutosMaisCaros(listaDePedidos, relatorio -> {
                    JOptionPane.showMessageDialog(null, relatorio);
                })).executa();
                new RelatorioProxy(new RelatorioClientesMaisLucrativos(listaDePedidos, relatorio -> {
                    JOptionPane.showMessageDialog(null, relatorio);
                })).executa();
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
