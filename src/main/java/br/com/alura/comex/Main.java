package br.com.alura.comex;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import br.com.alura.comex.utils.ProcessadorDeCSV;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        
        ArrayList<Pedido> pedidos = new ArrayList<>();
    	String arquivoCSV = "pedidos.csv";
    	
    	ProcessadorDeCSV processadorDeCSV = new ProcessadorDeCSV();
    	pedidos = processadorDeCSV.listarPedidos(arquivoCSV);

        
        System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", totalDePedidosRealizados);
        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", totalDeProdutosVendidos);
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", totalDeCategorias);
        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(montanteDeVendas.setScale(2, RoundingMode.HALF_DOWN)));
        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(Pedido.getPedidoMaisBarato(pedidos).get().getValorTotal()), Pedido.getPedidoMaisBarato(pedidos).get().getProduto());
        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(Pedido.getPedidoMaisCaro(pedidos).get().getValorTotal()), Pedido.getPedidoMaisCaro(pedidos).get().getProduto());
    }
}
