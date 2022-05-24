package br.com.alura.comex.processador;

import java.util.List;

import br.com.alura.comex.model.Pedido;

public interface Processador {
	abstract List<Pedido> lerRegistros() throws Exception;
}