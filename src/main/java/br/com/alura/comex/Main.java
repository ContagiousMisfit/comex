package br.com.alura.comex;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import br.com.alura.comex.relatorios.RelatorioSintetico;
import br.com.alura.comex.utils.ProcessadorDeCSV;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        
        ArrayList<Pedido> listaDePedidos = new ArrayList<>();
    	String arquivoCSV = "pedidos.csv";
    	
    	ProcessadorDeCSV processadorDeCSV = new ProcessadorDeCSV();
    	listaDePedidos = processadorDeCSV.listarPedidos(arquivoCSV);

    	RelatorioSintetico relatorio = new RelatorioSintetico(listaDePedidos);
        
        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", relatorio.getTotalDePedidosRealizados());
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", relatorio.getTotalDeProdutosVendidos());
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", relatorio.getTotalDeCategorias());
        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorio.getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)));
        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorio.getPedidoMaisBarato().get().getValorTotal()), relatorio.getPedidoMaisBarato().get().getProduto());
        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(relatorio.getPedidoMaisCaro().get().getValorTotal()), relatorio.getPedidoMaisCaro().get().getProduto());
    }
}
