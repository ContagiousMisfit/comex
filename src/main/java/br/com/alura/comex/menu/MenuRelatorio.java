package br.com.alura.comex.menu;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.processador.*;
import br.com.alura.comex.relatorios.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuRelatorio {

    Scanner input = new Scanner(System.in);

    private List<Pedido> escolherArquivo() throws Exception {
        imprimirOpcoesProcessador();
        System.out.print("Digite: ");
        String opcao = input.nextLine();
        CategoriaProcessador categoria = CategoriaProcessador.valueOf(opcao);
        Processador processador = categoria.getProcessador();
        return processador.lerRegistros();
    }

    private void escolherRelatorio(List<Pedido> listaDePedidos) {
        imprimirOpcoesRelatorio();
        System.out.print("Digite: ");
        String opcao = input.nextLine();
        if ("TODOS".equals(opcao))
            Arrays.stream(CategoriaRelatorio.values()).forEach(categoriaRelatorio -> categoriaRelatorio.getRelatorio(listaDePedidos).executa());
        else {
            CategoriaRelatorio categoria = CategoriaRelatorio.valueOf(opcao);
            Relatorio relatorio = categoria.getRelatorio(listaDePedidos);
            relatorio.executa();
        }
    }

    private void imprimirOpcoesProcessador() {
        System.out.println("----BEM VINDO----");
        System.out.println("Digite uma opção do menu abaixo em letras maiúsculas");
        System.out.println("Que tipo de arquivo deseja processar?");
        System.out.println("1) CSV");
        System.out.println("2) JSON");
        System.out.println("3) XML");
    }

    private void imprimirOpcoesRelatorio() {
        System.out.println("\nQual relatório deseja imprimir?");
        System.out.println("1 - Relatório sintético");
        System.out.println("2 - Relatório de clientes fiéis");
        System.out.println("3 - Relatório de vendas por categoria");
        System.out.println("4 - Relatório de produtos mais vendidos");
        System.out.println("5 - Relatório de produtos mais caros em cada categoria");
        System.out.println("6 - Relatório de clientes mais lucrativos");
        System.out.println("7 - TODOS");
    }

    public void executar() throws Exception {
        List<Pedido> listaDePedidos = escolherArquivo();
        escolherRelatorio(listaDePedidos);
    }


}
