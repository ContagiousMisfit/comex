package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.alura.comex.Pedido;

public class RelatorioClientesMaisLucrativos {

	public RelatorioClientesMaisLucrativos(List<Pedido> listaDePedidos) {

		Map<String, List<Pedido>> clientesMaisLucrativos = listaDePedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCliente));

		System.out.println("\n#### CLIENTES MAIS LUCRATIVOS");
		clientesMaisLucrativos
			.entrySet()
			.stream()
			.sorted(Map.Entry.<String, List<Pedido>>comparingByKey())
			.forEach(cliente -> {
			System.out.println("NOME: " + cliente.getKey() 
			+ "\nNº DE PEDIDOS: " + cliente.getValue().stream().map(pedido -> pedido.getQuantidade()).count()
			+ "\nMONTANTE GASTO: " + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(cliente.getValue().stream()
					.map(pedido -> pedido.getValorTotal()).reduce(BigDecimal.ZERO, BigDecimal::add))
			+ "\n");
		});
		
		//.sorted()
		//.filter(cliente -> cliente.getValue() > clientesMaisLucrativos.size()-3)
		
//		//Function<Pedido, BigDecimal> mapMontanteDeVendasPorCliente = pedido -> pedido.getValorTotal();
//		listaDePedidos
//				.stream()
//				.map(clientesMaisLucrativos)
//				.reduce(BigDecimal.ZERO, BigDecimal::add);
////		
//		listaDePedidos.stream().map(mapMontanteDeVendasPorCliente).reduce(BigDecimal.ZERO, BigDecimal::add);
		
//		public BigDecimal getMontanteDeVendas(List<Pedido> listaDePedidos) {
//			Function<Pedido, BigDecimal> mapaPedidos = pedido -> pedido.getValorTotal();
//			return montanteDeVendas = listaDePedidos
//					.stream()
//					.map(mapaPedidos)
//					.reduce(BigDecimal.ZERO, BigDecimal::add);
//		}
		
//		Map<String, Pedido> clientesMaisLucrativos = listaDePedidos.stream()
//				.collect(Collectors.toMap(Pedido::getCliente, Function.identity(),
//						BinaryOperator.maxBy(Comparator.comparing(Pedido::getPreco))));
//
//		clientesMaisLucrativos.entrySet().stream()
//			.sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
//			.forEach(entry -> {
//			System.out.println(
//					"NOME: " + entry.getValue().getCliente() 
//					+ "\nNº DE PEDIDOS: " + entry.getKey() 
//					+ "\nMONTANTE GASTO: " + entry.getValue()
//					+ "\n");
//		});
		
//		System.out.println("\n#### CLIENTES MAIS LUCRATIVOS \n");
//		clientesMaisLucrativos.entrySet().stream()
//			.sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
//			.filter(cliente -> cliente.getKey() > clientesMaisLucrativos.size()-2)	
//			.forEach(entry -> {
//			System.out.println(
//					"NOME: " + entry.getValue().getCliente() 
//					+ "\nNº DE PEDIDOS: " + entry.getKey() 
//					+ "\nMONTANTE GASTO: " + entry.getValue()
//					+ "\n");
//		});

	}
	
//	public BigDecimal getMontanteDeVendas(List<Pedido> listaDePedidos) {
//		Function<Pedido, BigDecimal> mapaPedidos = pedido -> pedido.getValorTotal();
//		return montanteDeVendas = listaDePedidos
//				.stream()
//				.map(mapaPedidos)
//				.reduce(BigDecimal.ZERO, BigDecimal::add);
//	}

}
