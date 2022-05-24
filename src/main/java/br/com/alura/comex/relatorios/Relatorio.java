package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import br.com.alura.comex.model.Pedido;

public abstract class Relatorio {

	public static List<Pedido> listaDePedidos = null;
	public Relatorio(List<Pedido> listaDePedidos) {
		Relatorio.listaDePedidos = listaDePedidos;
	}

	protected Relatorio() {
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

	public Pedido getPedidoMaisBarato() {
		return listaDePedidos.stream().min(Comparator.comparing(Pedido::getValorTotal)).orElseThrow(() -> new IllegalStateException("A lista de pedidos n�o deveria estar vazia."));
	}

	public Pedido getPedidoMaisCaro() {
		return listaDePedidos.stream().max(Comparator.comparing(Pedido::getValorTotal)).orElseThrow(() -> new IllegalStateException("A lista de pedidos n�o deveria estar vazia."));
	}
	public BigDecimal getMontanteDeVendas() {
		Function<Pedido, BigDecimal> mapaPedidos = pedido -> pedido.getValorTotal();
		return listaDePedidos
				.stream()
				.map(mapaPedidos)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getMontanteCliente(Map.Entry<String, List<Pedido>> cliente) {
		return cliente.getValue().stream().map(pedido -> pedido.getValorTotal())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public abstract void filtrarRelatorio();

	public abstract void imprimirRelatorio();

	public void executa() {
		filtrarRelatorio();
		imprimirRelatorio();
	}

}
