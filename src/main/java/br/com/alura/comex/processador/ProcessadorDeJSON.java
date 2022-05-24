package br.com.alura.comex.processador;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.comex.model.Pedido;

public class ProcessadorDeJSON implements Processador {

	static final String ARQUIVO_JSON = "pedidos.json";
	
	@Override
	public List<Pedido> lerRegistros() throws URISyntaxException, IOException {
		URL recursoJSON= ClassLoader.getSystemResource(ARQUIVO_JSON);
		FileReader reader = new FileReader(recursoJSON.toURI().getPath());
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(reader, new TypeReference<List<Pedido>>() {});
	}

}