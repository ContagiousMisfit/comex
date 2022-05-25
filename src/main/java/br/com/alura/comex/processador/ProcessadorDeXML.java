package br.com.alura.comex.processador;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.alura.comex.model.Pedido;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ProcessadorDeXML implements Processador {

	static final String ARQUIVO_XML = "pedidos.xml";
	
	@Override
	public List<Pedido> lerRegistros() throws URISyntaxException, IOException {
		URL recursoXML= ClassLoader.getSystemResource(ARQUIVO_XML);
		FileReader reader = new FileReader(recursoXML.toURI().getPath());
		XmlMapper objectMapper = new XmlMapper();
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper.readValue(reader, new TypeReference<List<Pedido>>() {});
	}

}
