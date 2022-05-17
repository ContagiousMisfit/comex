package br.com.alura.comex.relatorios;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.alura.comex.Pedido;

public class RelatorioFidelidade implements Relatorio{

	private Map<String, Long> clientesFieis;
	
	@Override
	public void filtrarRelatorio(List<Pedido> listaDePedidos) {
		clientesFieis = listaDePedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCliente, Collectors.counting()));		
	}

	@Override
	public void imprimirRelatorio() {
		System.out.println("\n#### RELAT�RIO DE CLIENTES FI�IS");
		clientesFieis.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByKey()).forEach(cliente -> {
			System.out.println("NOME: " + cliente.getKey() + "\nN� DE PEDIDOS: " + cliente.getValue() + "\n");
		});
	}
	
}
