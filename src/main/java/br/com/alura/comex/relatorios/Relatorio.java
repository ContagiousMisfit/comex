package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import br.com.alura.comex.model.Pedido;

public abstract class Relatorio {
	
	public static List<Pedido> listaDePedidos = null;
	int totalDeProdutosVendidos = 0;

	BigDecimal montanteDeVendas = BigDecimal.ZERO;
	public static final Pedido pedidoMaisBarato = new Pedido();
	public static final Pedido pedidoMaisCaro = new Pedido();

	public Relatorio(List<Pedido> listaDePedidos) {
		Relatorio.listaDePedidos = listaDePedidos;
	}
	
	public int getTotalDeProdutosVendidos() { 
		return listaDePedidos
				.stream()
				.mapToInt(pedido -> pedido.getQuantidade())
				.sum();
	}

	public int getTotalDePedidosRealizados() {
		return listaDePedidos.size();
	}

	public long getTotalDeCategorias() {
		return listaDePedidos.stream().distinct().count();
	}

	public BigDecimal getMontanteDeVendas() {
		Function<Pedido, BigDecimal> mapaPedidos = pedido -> pedido.getValorTotal();
		return listaDePedidos
				.stream()
				.map(mapaPedidos)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public Pedido getPedidoMaisBarato() {
		return listaDePedidos.stream().min(Comparator.comparing(Pedido::getValorTotal)).orElseThrow(() -> new IllegalStateException("A lista de pedidos não deveria estar vazia."));
	}

	public Pedido getPedidoMaisCaro() {
		return listaDePedidos.stream().max(Comparator.comparing(Pedido::getValorTotal)).orElseThrow(() -> new IllegalStateException("A lista de pedidos não deveria estar vazia."));
	}
	
	public void filtrarRelatorio() {
	}
	
	public void imprimirRelatorio() {
	}


}
