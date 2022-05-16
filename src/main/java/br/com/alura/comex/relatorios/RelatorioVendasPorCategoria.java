package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.alura.comex.Pedido;

public class RelatorioVendasPorCategoria {

	BigDecimal montanteDeVendas = BigDecimal.ZERO;

	public RelatorioVendasPorCategoria(List<Pedido> listaDePedidos) {

		Map<String, List<Pedido>> vendasPorCategoria = listaDePedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCategoria));

		System.out.println("\n#### RELATÓRIO DE VENDAS POR CATEGORIA\n");
	
		vendasPorCategoria.entrySet().stream().sorted(Map.Entry.<String, List<Pedido>>comparingByKey())
				.forEach(entry -> {
					System.out.println("CATEGORIA: " + entry.getKey() + "\nQUANTIDADE VENDIDA: "
							+ entry.getValue().stream().mapToInt(pedido -> pedido.getQuantidade()).sum()
							+ "\nMONTANTE: "
							+ entry.getValue().stream().map(pedido -> pedido.getValorTotal()).reduce(BigDecimal.ZERO, BigDecimal::add)
							+ "\n");
				});
	}

}
