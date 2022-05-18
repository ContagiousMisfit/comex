package br.com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;

public class Pedido {
	
	@CsvBindByName(column = "CATEGORIA", required=true)
	private String categoria;
	@CsvBindByName(column = "PRODUTO", required=true)
	private String produto;
	@CsvBindByName(column = "PRECO", required=true)
	private BigDecimal preco;
	@CsvBindByName(column = "QUANTIDADE", required=true)
	private int quantidade;
	@CsvBindByName(column = "DATA", required=true)
	private String data;
	@CsvBindByName(column = "CLIENTE", required=true)
	private String cliente;

	
	public Pedido() {
		super();
	}

	public Pedido(String categoria, String produto, String cliente, BigDecimal preco, int quantidade, String data) {
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

	public String getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Pedido{" + "categoria='" + categoria + '\'' + ", produto='" + produto + '\'' + ", cliente='" + cliente
				+ '\'' + ", preco=" + preco + ", quantidade=" + quantidade + ", data=" + data + '}';
	}
	
	public BigDecimal getValorTotal() {
		return getPreco().multiply(new BigDecimal(getQuantidade()));
	}

}