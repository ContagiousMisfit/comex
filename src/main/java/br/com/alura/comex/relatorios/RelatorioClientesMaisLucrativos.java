package br.com.alura.comex.relatorios;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.alura.comex.Pedido;

public class RelatorioClientesMaisLucrativos extends Relatorio {

	public RelatorioClientesMaisLucrativos(List<Pedido> listaDePedidos) {

		Map<String, List<Pedido>> produtosVendidos = listaDePedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCliente));

		System.out.println("\n#### CLIENTES MAIS LUCRATIVOS \n");
		produtosVendidos.entrySet().stream().sorted(Map.Entry.<String, List<Pedido>>comparingByKey()).forEach(entry -> {
			System.out.println("NOME: " + entry.getKey() 
					+ "\nNº DE PEDIDOS: " + entry.getValue() 
					+ "\nMONTANTE GASTO: " + entry.getValue().get(0).getPreco()
					+ "\n");
		});

	}

	@Override
	public <T> Map<T, T> filtrarRelatorio() {
		return null;
	}

	@Override
	public void imprimirRelatorio() {
		
	}
}
