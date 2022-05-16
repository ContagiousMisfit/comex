package br.com.alura.comex.relatorios;

import java.util.ArrayList;

import br.com.alura.comex.Pedido;

public class RelatorioFidelidade {
	
	int totalDePedidosRealizados = 0;
	String nomeCliente;
	
	public RelatorioFidelidade(ArrayList<Pedido> pedidos) {
		super();
		this.nomeCliente = pedidos.forEach(pedido -> pedido.getCliente());
	}
	
	
	

}
