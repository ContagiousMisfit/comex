package br.com.alura.comex.processador;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProcessadorDeJSON extends ProcessadorJackson {

	private static final String ARQUIVO_JSON = "pedidos.json";

	@Override
	public ObjectMapper getMapper() {
		return new ObjectMapper();
	}

	@Override
	public String getNomeArquivo() {
		return ARQUIVO_JSON;
	}
}