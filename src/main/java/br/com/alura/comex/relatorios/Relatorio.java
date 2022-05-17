package br.com.alura.comex.relatorios;

import java.util.List;

import br.com.alura.comex.Pedido;

public interface Relatorio {
	
	public static final List<Pedido> listaDePedidos = null; 

	public abstract void filtrarRelatorio(List<Pedido> listaDePedidos);
	
	public abstract void imprimirRelatorio();


}
