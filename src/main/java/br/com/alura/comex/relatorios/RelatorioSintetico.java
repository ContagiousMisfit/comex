package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.alura.comex.CategoriasProcessadas;
import br.com.alura.comex.Pedido;

public class RelatorioSintetico {

	int totalDeProdutosVendidos = 0;
	int totalDePedidosRealizados = 0;
	int totalDeCategorias = 0;

	CategoriasProcessadas categoriasProcessadas = new CategoriasProcessadas();

	BigDecimal montanteDeVendas = BigDecimal.ZERO;
	Optional<Pedido> pedidoMaisBarato = null;
	Optional<Pedido> pedidoMaisCaro = null;

	public RelatorioSintetico(ArrayList<Pedido> pedidos) {
		super();
		this.totalDePedidosRealizados = pedidos.size();
		//Mapa de big decimals
		Function<Pedido, BigDecimal> mapaPedidos = pedido -> pedido.getValorTotal();
		this.totalDeProdutosVendidos = pedidos
				.stream()
				.mapToInt(pedido -> pedido.getQuantidade())
				.sum();
		pedidos.forEach(pedido -> categoriasProcessadas.add(pedido.getCategoria()));
		this.totalDeCategorias = categoriasProcessadas.size();
		this.montanteDeVendas = pedidos
				.stream()
				.map(mapaPedidos)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		this.pedidoMaisBarato = Pedido.getPedidoMaisBarato(pedidos);
		this.pedidoMaisCaro = Pedido.getPedidoMaisCaro(pedidos);
	}

	public Optional<Pedido> getPedidoMaisBarato() {
		return pedidoMaisBarato;
	}


	public void setPedidoMaisBarato(Optional<Pedido> pedidoMaisBarato) {
		this.pedidoMaisBarato = pedidoMaisBarato;
	}


	public Optional<Pedido> getPedidoMaisCaro() {
		return pedidoMaisCaro;
	}


	public void setPedidoMaisCaro(Optional<Pedido> pedidoMaisCaro) {
		this.pedidoMaisCaro = pedidoMaisCaro;
	}


	public int getTotalDeProdutosVendidos() {
		return totalDeProdutosVendidos;
	}

	public void setTotalDeProdutosVendidos(int totalDeProdutosVendidos) {
		this.totalDeProdutosVendidos = totalDeProdutosVendidos;
	}

	public int getTotalDePedidosRealizados() {
		return totalDePedidosRealizados;
	}

	public void setTotalDePedidosRealizados(int totalDePedidosRealizados) {
		this.totalDePedidosRealizados = totalDePedidosRealizados;
	}

	public int getTotalDeCategorias() {
		return totalDeCategorias;
	}

	public void setTotalDeCategorias(int totalDeCategorias) {
		this.totalDeCategorias = totalDeCategorias;
	}

	public CategoriasProcessadas getCategoriasProcessadas() {
		return categoriasProcessadas;
	}

	public void setCategoriasProcessadas(CategoriasProcessadas categoriasProcessadas) {
		this.categoriasProcessadas = categoriasProcessadas;
	}

	public BigDecimal getMontanteDeVendas() {
		return montanteDeVendas;
	}

	public void setMontanteDeVendas(BigDecimal montanteDeVendas) {
		this.montanteDeVendas = montanteDeVendas;
	}

}
