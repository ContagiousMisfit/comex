package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.utils.Formatador;

public class RelatorioVendasPorCategoria extends Relatorio {

	public RelatorioVendasPorCategoria(List<Pedido> listaDePedidos) {
		super(listaDePedidos);
	}

	private Map<String, List<Pedido>> vendasPorCategoria;

	@Override
	public void filtrarRelatorio() {
		vendasPorCategoria = listaDePedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCategoria));
	}

	@Override
	public void imprimirRelatorio() {
		System.out.println("\n#### RELATÓRIO DE VENDAS POR CATEGORIA");
		vendasPorCategoria.entrySet().stream().sorted(Map.Entry.<String, List<Pedido>>comparingByKey())
				.forEach(cliente -> {
					System.out.println("CATEGORIA: " + cliente.getKey()
							+ "\nQUANTIDADE VENDIDA: "+ cliente.getValue().stream().mapToInt(pedido -> pedido.getQuantidade()).sum()
							+ "\nMONTANTE: " + Formatador.formatarValorTotal(getMontanteCliente(cliente))
							+ "\n");
				});
	}


}
