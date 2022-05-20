package br.com.alura.comex.utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.comex.Pedido;

public class ProcessadorDeJSON implements Processador {

	static final String ARQUIVO_CSV = "pedidos.json";
	
	@Override
	public List<Pedido> lerRegistros() throws URISyntaxException, StreamReadException, DatabindException, IOException {
		URL recursoCSV = ClassLoader.getSystemResource(ARQUIVO_CSV);
		FileReader reader = new FileReader(recursoCSV.toURI().getPath());
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(reader, new TypeReference<List<Pedido>>() {});
	}

}