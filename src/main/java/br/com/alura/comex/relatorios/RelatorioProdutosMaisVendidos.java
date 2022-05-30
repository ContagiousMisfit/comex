package br.com.alura.comex.relatorios;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import br.com.alura.comex.model.Pedido;

public class RelatorioProdutosMaisVendidos extends Relatorio {

	private final Consumer<String> impressoraDoRelatorio;

	private List<ProdutosMaisVendidos> produtosMaisVendidos;

	public RelatorioProdutosMaisVendidos(List<Pedido> listaDePedidos, Consumer<String> impressoraDoRelatorio) {
		super(listaDePedidos);
		this.impressoraDoRelatorio = impressoraDoRelatorio;
	}

	public List<RelatorioProdutosMaisVendidos.ProdutosMaisVendidos> getProdutosMaisVendidos() {
		return produtosMaisVendidos;
	}


	@Override
	public void filtrarRelatorio() {
		if (listaDePedidos == null)
			throw new IllegalArgumentException("A lista de pedidos de um relatório não pode ser nula!");
		if (listaDePedidos.isEmpty())
			throw new IllegalArgumentException("A lista não pode estar vazia!");
		produtosMaisVendidos = listaDePedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getQuantidade))
				.entrySet()
				.stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
				.limit(3)
				.map(produtosMaisVendidos -> {
					String produto = produtosMaisVendidos.getValue().get(0).getProduto();
					int quantidade = produtosMaisVendidos.getKey();
					return new ProdutosMaisVendidos(produto, quantidade);
				}).toList();
	}

	@Override
	public void imprimirRelatorio() {
		impressoraDoRelatorio.accept("\n#### TOP 3 PRODUTOS MAIS VENDIDOS");
		produtosMaisVendidos
			.forEach(produtosMaisVendidos -> {
				impressoraDoRelatorio.accept(
					"PRODUTO: " + produtosMaisVendidos.getProduto()
					+ "\nQUANTIDADE VENDIDA: " + produtosMaisVendidos.getQuantidadeVendida()
					+ "\n");
		});
	}

	public class ProdutosMaisVendidos {

		private final String produto;
		private final int quantidadeVendida;

		public ProdutosMaisVendidos(String produto, int quantidadeVendida) {
			this.produto = produto;
			this.quantidadeVendida = quantidadeVendida;
		}

		public String getProduto() {
			return produto;
		}

		public int getQuantidadeVendida() {
			return quantidadeVendida;
		}
	}
}
