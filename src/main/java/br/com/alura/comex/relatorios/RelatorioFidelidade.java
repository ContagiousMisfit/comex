package br.com.alura.comex.relatorios;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import br.com.alura.comex.model.Pedido;

public class RelatorioFidelidade extends Relatorio{

	public RelatorioFidelidade(List<Pedido> listaDePedidos) {
		super(listaDePedidos);
	}

	private TreeMap<String, Long> clientesFieis;
	
	@Override
	public void filtrarRelatorio() {
		clientesFieis = listaDePedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCliente, TreeMap::new, Collectors.counting()));		
	}

	@Override
	public void imprimirRelatorio() {
		System.out.println("\n#### RELAT�RIO DE CLIENTES FI�IS");
		clientesFieis.entrySet().forEach(cliente -> {
			System.out.println("NOME: " + cliente.getKey() + "\nN� DE PEDIDOS: " + cliente.getValue() + "\n");
		});
	}
	
}
