package br.com.alura.comex;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.alura.comex.relatorios.Relatorio;
import br.com.alura.comex.relatorios.RelatorioClientesMaisLucrativos;
import br.com.alura.comex.relatorios.RelatorioFidelidade;
import br.com.alura.comex.relatorios.RelatorioProdutosMaisCaros;
import br.com.alura.comex.relatorios.RelatorioProdutosMaisVendidos;
import br.com.alura.comex.relatorios.RelatorioSintetico;
import br.com.alura.comex.relatorios.RelatorioVendasPorCategoria;
import br.com.alura.comex.utils.ProcessadorDeCSV;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        
    	String arquivoCSV = "pedidos.csv";
    	ProcessadorDeCSV processadorDeCSV = new ProcessadorDeCSV();
    	List<Pedido> listaDePedidos = processadorDeCSV.listarPedidos(arquivoCSV);

    	RelatorioSintetico relatorioSintetico = new RelatorioSintetico(listaDePedidos);
        
        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", relatorioSintetico.getTotalDePedidosRealizados(listaDePedidos));
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", relatorioSintetico.getTotalDeProdutosVendidos(listaDePedidos));
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", relatorioSintetico.getTotalDeCategorias(listaDePedidos));
        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioSintetico.getMontanteDeVendas(listaDePedidos).setScale(2, RoundingMode.HALF_DOWN)));
        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioSintetico.getPedidoMaisBarato(listaDePedidos).getValorTotal()), relatorioSintetico.getPedidoMaisBarato(listaDePedidos).getProduto());
        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorioSintetico.getPedidoMaisCaro(listaDePedidos).getValorTotal()), relatorioSintetico.getPedidoMaisCaro(listaDePedidos).getProduto());
    
        Relatorio relatorioFidelidade = new RelatorioFidelidade();
        relatorioFidelidade.filtrarRelatorio(listaDePedidos);
        relatorioFidelidade.imprimirRelatorio();
        
        Relatorio relatorioVendasPorCategoria = new RelatorioVendasPorCategoria();
        relatorioVendasPorCategoria.filtrarRelatorio(listaDePedidos);
        relatorioVendasPorCategoria.imprimirRelatorio();
        
        Relatorio relatorioProdutosMaisVendidos = new RelatorioProdutosMaisVendidos();
        relatorioProdutosMaisVendidos.filtrarRelatorio(listaDePedidos);
        relatorioProdutosMaisVendidos.imprimirRelatorio();
        
        Relatorio relatorioProdutosMaisCaros = new RelatorioProdutosMaisCaros();
        relatorioProdutosMaisCaros.filtrarRelatorio(listaDePedidos);
        relatorioProdutosMaisCaros.imprimirRelatorio();
        
        Relatorio relatorioClientesMaisLucrativos = new RelatorioClientesMaisLucrativos();
        relatorioClientesMaisLucrativos.filtrarRelatorio(listaDePedidos);
        relatorioClientesMaisLucrativos.imprimirRelatorio();
        
    }
}
