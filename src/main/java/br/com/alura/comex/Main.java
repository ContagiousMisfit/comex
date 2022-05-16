package br.com.alura.comex;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.alura.comex.relatorios.RelatorioFidelidade;
import br.com.alura.comex.relatorios.RelatorioProdutosMaisVendidos;
import br.com.alura.comex.relatorios.RelatorioSintetico;
import br.com.alura.comex.relatorios.RelatorioVendasPorCategoria;
import br.com.alura.comex.utils.ProcessadorDeCSV;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        
    	String arquivoCSV = "pedidos.csv";
    	ProcessadorDeCSV processadorDeCSV = new ProcessadorDeCSV();
    	List<Pedido> listaDePedidos = processadorDeCSV.listarPedidos(arquivoCSV);

    	RelatorioSintetico relatorio = new RelatorioSintetico(listaDePedidos);
        
        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", relatorio.getTotalDePedidosRealizados(listaDePedidos));
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", relatorio.getTotalDeProdutosVendidos(listaDePedidos));
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", relatorio.getTotalDeCategorias(listaDePedidos));
        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorio.getMontanteDeVendas(listaDePedidos).setScale(2, RoundingMode.HALF_DOWN)));
        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorio.getPedidoMaisBarato(listaDePedidos).getValorTotal()), relatorio.getPedidoMaisBarato(listaDePedidos).getProduto());
        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorio.getPedidoMaisCaro(listaDePedidos).getValorTotal()), relatorio.getPedidoMaisCaro(listaDePedidos).getProduto());
    
        RelatorioFidelidade relatorioFidelidade = new RelatorioFidelidade(listaDePedidos);
        RelatorioVendasPorCategoria relatorioVendasPorCategoria = new RelatorioVendasPorCategoria(listaDePedidos);
        //RelatorioProdutosMaisVendidos relatorioProdutosMaisVendidos = new RelatorioProdutosMaisVendidos(listaDePedidos);
    }
}
