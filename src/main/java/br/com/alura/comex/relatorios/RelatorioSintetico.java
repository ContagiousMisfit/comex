package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.alura.comex.Pedido;

public class RelatorioSintetico implements Relatorio {

	List<Pedido> listaDePedidos;
	int totalDeProdutosVendidos = 0;
	int totalDePedidosRealizados = 0;
	int totalDeCategorias = 0;

	HashSet<String> categoriasProcessadas = new HashSet<String>();

	BigDecimal montanteDeVendas = BigDecimal.ZERO;
	Pedido pedidoMaisBarato;
	Pedido pedidoMaisCaro;

	public int getTotalDeProdutosVendidos(List<Pedido> listaDePedidos) { 
		return this.totalDeProdutosVendidos = listaDePedidos
				.stream()
				.mapToInt(pedido -> pedido.getQuantidade())
				.sum();
	}

	public int getTotalDePedidosRealizados(List<Pedido> listaDePedidos) {
		return listaDePedidos.size();
	}

	public int getTotalDeCategorias(List<Pedido> listaDePedidos) {
		listaDePedidos.forEach(pedido -> categoriasProcessadas.add(pedido.getCategoria()));
		return categoriasProcessadas.size();
	}

	public HashSet<String> getCategoriasProcessadas() {
		return categoriasProcessadas;
	}

	public BigDecimal getMontanteDeVendas(List<Pedido> listaDePedidos) {
		Function<Pedido, BigDecimal> mapaPedidos = pedido -> pedido.getValorTotal();
		return montanteDeVendas = listaDePedidos
				.stream()
				.map(mapaPedidos)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public Pedido getPedidoMaisBarato(List<Pedido> listaDePedidos) {
		return listaDePedidos.stream().min(Comparator.comparing(Pedido::getValorTotal)).orElseThrow(() -> new IllegalStateException("A lista de pedidos não deveria estar vazia."));
	}

	public Pedido getPedidoMaisCaro(List<Pedido> listaDePedidos) {
		return listaDePedidos.stream().max(Comparator.comparing(Pedido::getValorTotal)).orElseThrow(() -> new IllegalStateException("A lista de pedidos não deveria estar vazia."));
	}

	@Override
	public void filtrarRelatorio(List<Pedido> listaAtual) {
		listaDePedidos = listaAtual.stream()
				.collect(Collectors.toList());				
	}

	@Override
	public void imprimirRelatorio() {
		
		  System.out.println("#### RELATÓRIO DE VALORES TOTAIS");
	        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", getTotalDePedidosRealizados(listaDePedidos));
	        System.out.printf("- TOTAL DE PRODUTOS VENDIDOS: %s\n", getTotalDeProdutosVendidos(listaDePedidos));
	        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", getTotalDeCategorias(listaDePedidos));
	        System.out.printf("- MONTANTE DE VENDAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(getMontanteDeVendas(listaDePedidos).setScale(2, RoundingMode.HALF_DOWN)));
	        System.out.printf("- PEDIDO MAIS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(getPedidoMaisBarato(listaDePedidos).getValorTotal()), getPedidoMaisBarato(listaDePedidos).getProduto());
	        System.out.printf("- PEDIDO MAIS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(getPedidoMaisCaro(listaDePedidos).getValorTotal()), getPedidoMaisCaro(listaDePedidos).getProduto());
	    			
	}

	

}
