package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

import br.com.alura.comex.Pedido;

public class RelatorioSintetico {

	List<Pedido> listaDePedidos;
	int totalDeProdutosVendidos = 0;
	int totalDePedidosRealizados = 0;
	int totalDeCategorias = 0;

	HashSet<String> categoriasProcessadas = new HashSet<String>();

	BigDecimal montanteDeVendas = BigDecimal.ZERO;
	Pedido pedidoMaisBarato;
	Pedido pedidoMaisCaro;

	public RelatorioSintetico(List<Pedido> listaDePedidos) {
		super();
		//verificação - programação defensiva
		if (listaDePedidos == null || listaDePedidos.isEmpty()) throw new IllegalArgumentException("A lista de pedidos não pode ser nula nem vazia!");
		
		this.totalDePedidosRealizados = getTotalDePedidosRealizados(listaDePedidos);
		this.totalDeProdutosVendidos = getTotalDeProdutosVendidos(listaDePedidos);
		this.totalDeCategorias = getTotalDeCategorias(listaDePedidos);
		this.montanteDeVendas = getMontanteDeVendas(listaDePedidos);
		this.pedidoMaisBarato = getPedidoMaisBarato(listaDePedidos);
		this.pedidoMaisCaro = getPedidoMaisCaro(listaDePedidos);
	}

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

	

}
