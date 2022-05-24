package br.com.alura.comex.relatorios;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.utils.Formatador;

public class RelatorioProdutosMaisCaros extends Relatorio {

	public RelatorioProdutosMaisCaros(List<Pedido> listaDePedidos) {
		super(listaDePedidos);
	}

	TreeMap<String, Pedido> produtosMaisCaros;

	@Override
	public void filtrarRelatorio() {
		produtosMaisCaros = listaDePedidos.stream().collect(Collectors.toMap(Pedido::getCategoria, Function.identity(),
				BinaryOperator.maxBy(Comparator.comparing(Pedido::getPreco)),TreeMap::new));

	}

	@Override
	public void imprimirRelatorio() {
		System.out.println("\n#### PRODUTO MAIS CARO EM CADA CATEGORIA");
		produtosMaisCaros.entrySet().stream().forEach(
						entry -> System.out.println("CATEGORIA: " + entry.getKey() 
						+ "\nPRODUTO: " + entry.getValue().getProduto() 
						+ "\nPRE�O: " + Formatador.formatarValorTotal(entry.getValue().getPreco())
						+ "\n"));
	}
}
