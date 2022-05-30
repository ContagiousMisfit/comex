package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.utils.Formatador;

public class RelatorioProdutosMaisCaros extends Relatorio {

	private final Consumer<String> impressoraDoRelatorio;
	private List<ProdutosMaisCaros> produtosMaisCaros;

	public RelatorioProdutosMaisCaros(List<Pedido> listaDePedidos, Consumer<String> impressoraDoRelatorio) {
		super(listaDePedidos);
		this.impressoraDoRelatorio = impressoraDoRelatorio;
	}

	public List<ProdutosMaisCaros> getProdutosMaisCaros() {
		return produtosMaisCaros;
	}

	@Override
	public void filtrarRelatorio() {
		if (listaDePedidos == null)
			throw new IllegalArgumentException("A lista de pedidos de um relatório não pode ser nula!");
		if (listaDePedidos.isEmpty())
			throw new IllegalArgumentException("A lista não pode estar vazia!");
		produtosMaisCaros = listaDePedidos.stream().collect(Collectors.toMap(Pedido::getCategoria, Function.identity(),
				BinaryOperator.maxBy(Comparator.comparing(Pedido::getPreco)),TreeMap::new)).entrySet().stream()
				.map(entry -> {
					String categoria = entry.getKey();
					String produto = entry.getValue().getProduto();
					BigDecimal preco = entry.getValue().getPreco();
					return new ProdutosMaisCaros(categoria, produto, preco);
				}).toList();

	}

	@Override
	public void imprimirRelatorio() {
		impressoraDoRelatorio.accept("\n#### PRODUTO MAIS CARO EM CADA CATEGORIA");
		produtosMaisCaros.stream().forEach(produtosMaisCaros -> {
			impressoraDoRelatorio.accept("CATEGORIA: " + produtosMaisCaros.getCategoria()
					+ "\nPRODUTO: " + produtosMaisCaros.getProduto()
					+ "\nPREÇO: " + Formatador.formatarValorTotal(produtosMaisCaros.getPreco())
					+ "\n");
		});
	}

	public class ProdutosMaisCaros {

		private final String categoria;
		private final String produto;
		private final BigDecimal preco;

		public ProdutosMaisCaros(String categoria, String produto, BigDecimal preco) {
			this.categoria = categoria;
			this.produto = produto;
			this.preco = preco;
		}

		public String getCategoria() {
			return categoria;
		}

		public String getProduto() {
			return produto;
		}

		public BigDecimal getPreco() {
			return preco;
		}
	}
}
