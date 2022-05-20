package br.com.alura.comex.relatorios;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.alura.comex.model.Pedido;

public class RelatorioProdutosMaisVendidos extends Relatorio {

	public RelatorioProdutosMaisVendidos(List<Pedido> listaDePedidos) {
		super(listaDePedidos);
	}

	Map<Integer, List<Pedido>> produtosVendidos;

	@Override
	public void filtrarRelatorio() {
		produtosVendidos = listaDePedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getQuantidade));
	}
	
	@Override
	public void imprimirRelatorio() {
		System.out.println("\n#### TOP 3 PRODUTOS MAIS VENDIDOS");

		produtosVendidos
			.entrySet()
			.stream()
			.sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
			.filter(produto -> produto.getKey() > produtosVendidos.size()-3)
			.forEach(entry -> {
			System.out.println(
					"PRODUTO: " + entry.getValue().get(0).getProduto() 
					+ "\nQUANTIDADE VENDIDA: " + entry.getKey()
					+ "\n");
		});		
	}

}
