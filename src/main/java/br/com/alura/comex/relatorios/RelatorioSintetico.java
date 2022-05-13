package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

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

	public RelatorioSintetico(ArrayList<Pedido> pedidos, int totalDeProdutosVendidos, int totalDePedidosRealizados, int totalDeCategorias,
			CategoriasProcessadas categoriasProcessadas, BigDecimal montanteDeVendas, Pedido pedidoMaisBarato,
			Pedido pedidoMaisCaro) {
		super();
		//Mapa de big decimals
		Function<Pedido, BigDecimal> mapaPedidos = pedido -> pedido.getValorTotal();

		this.totalDeProdutosVendidos = pedidos
				.stream()
				.mapToInt(pedido -> pedido.getQuantidade())
				.sum();
		this.totalDePedidosRealizados = pedidos.size();
		this.totalDeCategorias = totalDeCategorias;
		this.categoriasProcessadas = categoriasProcessadas;
		this.montanteDeVendas = pedidos
				.stream()
				.map(mapaPedidos)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		this.pedidoMaisBarato = Pedido.getPedidoMaisBarato(pedidos);
		this.pedidoMaisCaro = Pedido.getPedidoMaisCaro(pedidos);
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
