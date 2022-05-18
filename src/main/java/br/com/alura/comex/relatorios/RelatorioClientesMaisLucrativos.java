package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.alura.comex.Pedido;

public class RelatorioClientesMaisLucrativos implements Relatorio {

	Map<String, List<Pedido>> clientesMaisLucrativos;

	@Override
	public void filtrarRelatorio(List<Pedido> listaDePedidos) {
		clientesMaisLucrativos = listaDePedidos.stream().collect(Collectors.groupingBy(Pedido::getCliente));
	}

	@Override
	public void imprimirRelatorio() {
		System.out.println("\n#### CLIENTES MAIS LUCRATIVOS");
		clientesMaisLucrativos.entrySet().stream().sorted(Map.Entry.<String, List<Pedido>>comparingByKey()).filter(cliente -> cliente.getValue().stream().map(pedido -> pedido.getQuantidade()).count() > 3)
				.forEach(cliente -> {
					System.out.println("NOME: " + cliente.getKey() + "\nNº DE PEDIDOS: "
							+ cliente.getValue().stream().map(pedido -> pedido.getQuantidade()).count()
							+ "\nMONTANTE GASTO: "
							+ NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
									.format(cliente.getValue().stream().map(pedido -> pedido.getValorTotal())
											.reduce(BigDecimal.ZERO, BigDecimal::add))
							+ "\n");
				});
	}

}
