package br.com.alura.comex.menu;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.relatorios.*;
import br.com.alura.comex.utils.Processador;
import br.com.alura.comex.utils.ProcessadorDeCSV;
import br.com.alura.comex.utils.ProcessadorDeJSON;
import br.com.alura.comex.utils.ProcessadorDeXML;

import java.util.List;
import java.util.Scanner;

public class MenuRelatorio {

    Scanner sc = new Scanner(System.in);

    private List<Pedido> escolherArquivo() throws Exception {
        imprimirOpcoesProcessador();
        System.out.print("Digite: ");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                return ((Processador) new ProcessadorDeCSV()).lerRegistros();
            case 2:
                return ((Processador) new ProcessadorDeJSON()).lerRegistros();
            case 3:
                return ((Processador) new ProcessadorDeXML()).lerRegistros();
        }
        return null;
    }

    private void escolherRelatorio(List<Pedido> listaDePedidos) {
        imprimirOpcoesRelatorio();
        System.out.print("Digite: ");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                new RelatorioSintetico(listaDePedidos).executa();
                break;
            case 2:
                new RelatorioFidelidade(listaDePedidos).executa();
                break;
            case 3:
                new RelatorioVendasPorCategoria(listaDePedidos).executa();
                break;
            case 4:
                new RelatorioProdutosMaisVendidos(listaDePedidos).executa();
                break;
            case 5:
                new RelatorioProdutosMaisCaros(listaDePedidos).executa();
                break;
            case 6:
                new RelatorioClientesMaisLucrativos(listaDePedidos).executa();
                break;
        }
    }

    private void imprimirOpcoesProcessador() {
        System.out.println("----BEM VINDO----");
        System.out.println("Escolha uma opção no menu abaixo:");
        System.out.println("Que tipo de arquivo deseja processar?");
        System.out.println("1 - CSV");
        System.out.println("2 - JSON");
        System.out.println("3 - XML");
    }

    private void imprimirOpcoesRelatorio() {
        System.out.println("\nQual relatório deseja imprimir?");
        System.out.println("1 - Relatório sintético");
        System.out.println("2 - Relatório de clientes fiéis");
        System.out.println("3 - Relatório de vendas por categoria");
        System.out.println("4 - Relatório de produtos mais vendidos");
        System.out.println("5 - Relatório de produtos mais caros em cada categoria");
        System.out.println("6 - Relatório de clientes mais lucrativos");
    }

    public void executar() throws Exception {
        List<Pedido> listaDePedidos = escolherArquivo();
        escolherRelatorio(listaDePedidos);
    }


}
