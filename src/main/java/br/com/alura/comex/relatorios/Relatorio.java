package br.com.alura.comex.relatorios;

import java.util.Map;

public abstract class Relatorio {
	
	public abstract <T> Map<T, T> filtrarRelatorio();
	
	public abstract void imprimirRelatorio();

}
