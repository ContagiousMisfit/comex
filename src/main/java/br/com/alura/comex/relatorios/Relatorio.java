package br.com.alura.comex.relatorios;

import java.util.List;

import br.com.alura.comex.Pedido;

public interface Relatorio {
	
	public static final List<Pedido> listaDePedidos = null; 

	public void filtrarRelatorio(List<Pedido> listaDePedidos);
	
	public void imprimirRelatorio();


}
