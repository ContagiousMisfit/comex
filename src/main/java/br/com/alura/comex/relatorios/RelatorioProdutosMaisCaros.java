package br.com.alura.comex.relatorios;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.alura.comex.Pedido;

public class RelatorioProdutosMaisCaros {

	public RelatorioProdutosMaisCaros(List<Pedido> listaDePedidos) {

		System.out.println("\n#### PRODUTO MAIS CARO EM CADA CATEGORIA");

		Map<String, Pedido> produtosMaisCaros = listaDePedidos
				.stream()
				.collect(Collectors.toMap(Pedido::getCategoria,
				Function.identity(), BinaryOperator.maxBy(Comparator.comparing(Pedido::getPreco))));
		
		produtosMaisCaros
			.entrySet()
			.stream()
			.sorted(Map.Entry.<String, Pedido>comparingByKey())
			.forEach(entry -> System.out.println("CATEGORIA: " + entry.getKey() + "\nPRODUTO: "
				+ entry.getValue().getProduto() + "\nPREÇO:" + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(entry.getValue().getPreco()) + "\n"));

	}

}
