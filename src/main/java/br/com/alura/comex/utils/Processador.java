package br.com.alura.comex.utils;

import java.util.List;

import br.com.alura.comex.Pedido;

public interface Processador {
	
	static final String arquivoCSV = "";

	abstract List<Pedido> lerRegistros() throws Exception;
}
