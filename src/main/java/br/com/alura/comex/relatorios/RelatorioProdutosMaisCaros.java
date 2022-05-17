package br.com.alura.comex.relatorios;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.alura.comex.Pedido;

public class RelatorioProdutosMaisCaros {

	public RelatorioProdutosMaisCaros(List<Pedido> listaDePedidos) {

		Map<String, List<Pedido>> produtosVendidos = listaDePedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCategoria));

		System.out.println("\n#### PRODUTO MAIS CARO EM CADA CATEGORIA \n");
		produtosVendidos
			.entrySet()
			.stream()
			.forEach(entry -> {
			System.out.println(
					"CATEGORIA: " + entry.getKey() 
					+ "\nPRODUTO: " + entry.getValue()
					+ "\nPREÇO: " + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(entry.getValue().get(0).getPreco())
					+ "\n");
		});
//		unSortedMap.entrySet()
//		  .stream()
//		  .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
//		  .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

	}

	
}
