package br.com.alura.comex.relatorios;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.alura.comex.model.Pedido;

public class RelatorioSintetico extends Relatorio {

	public RelatorioSintetico(List<Pedido> listaDePedidos) {
		super(listaDePedidos);
	}

	@Override
	public void imprimirRelatorio() {
		
		  System.out.println("#### RELAT?RIO DE VALORES TOTAIS");
	        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", getTotalDePedidosRealizados());
	        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", getTotalDeProdutosVendidos());
	        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", getTotalDeCategorias());
	        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(getMontanteDeVendas().setScale(2, RoundingMode.HALF_DOWN)));
	        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(getPedidoMaisBarato().getValorTotal()), getPedidoMaisBarato().getProduto());
	        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(getPedidoMaisCaro().getValorTotal()), getPedidoMaisCaro().getProduto());
	    			
	}

}
