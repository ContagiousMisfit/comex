package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.alura.comex.Pedido;

public class RelatorioVendasPorCategoria implements Relatorio {

	Map<String, List<Pedido>> vendasPorCategoria;

	@Override
	public void filtrarRelatorio(List<Pedido> listaDePedidos) {
		vendasPorCategoria = listaDePedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCategoria));
	}

	@Override
	public void imprimirRelatorio() {
		System.out.println("\n#### RELATÓRIO DE VENDAS POR CATEGORIA");
		vendasPorCategoria.entrySet().stream().sorted(Map.Entry.<String, List<Pedido>>comparingByKey())
				.forEach(entry -> {
					System.out.println("CATEGORIA: " + entry.getKey() 
							+ "\nQUANTIDADE VENDIDA: "+ entry.getValue().stream().mapToInt(pedido -> pedido.getQuantidade()).sum()
							+ "\nMONTANTE: " + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(entry.getValue().stream()
												.map(pedido -> pedido.getValorTotal()).reduce(BigDecimal.ZERO, BigDecimal::add))
							+ "\n");
				});
	}
	

}
