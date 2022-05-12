package br.com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Pedido {

	private String categoria;
	private String produto;
	private String cliente;

	private BigDecimal preco;
	private int quantidade;

	private LocalDate data;

	public Pedido(String categoria, String produto, String cliente, BigDecimal preco, int quantidade, LocalDate data) {
		this.categoria = categoria;
		this.produto = produto;
		this.cliente = cliente;
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getProduto() {
		return produto;
	}

	public String getCliente() {
		return cliente;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public LocalDate getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Pedido{" + "categoria='" + categoria + '\'' + ", produto='" + produto + '\'' + ", cliente='" + cliente
				+ '\'' + ", preco=" + preco + ", quantidade=" + quantidade + ", data=" + data + '}';
	}

	public BigDecimal getValorTotal(ArrayList<Pedido> pedidos) {
		return null;

	}

	public static Optional<Pedido> getPedidoMaisBarato(ArrayList<Pedido> pedidos) {

		return Optional.of(
				pedidos.stream().min(Comparator.comparing(Pedido::getPreco)).orElseThrow(NoSuchElementException::new));

	}

	public static Optional<Pedido> getPedidoMaisCaro(ArrayList<Pedido> pedidos) {

		return Optional.of(
				pedidos.stream().max(Comparator.comparing(Pedido::getPreco)).orElseThrow(NoSuchElementException::new));

	}

}
