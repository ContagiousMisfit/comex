package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.utils.Formatador;

public class RelatorioClientesMaisLucrativos extends Relatorio {

	public RelatorioClientesMaisLucrativos(List<Pedido> listaDePedidos) {super(listaDePedidos);}

	Map<String, List<Pedido>> clientesMaisLucrativos;

	@Override
	public void filtrarRelatorio() {
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
							+ Formatador.formatarValorTotal(getMontanteCliente(cliente))
							+ "\n");
				});
	}

}
