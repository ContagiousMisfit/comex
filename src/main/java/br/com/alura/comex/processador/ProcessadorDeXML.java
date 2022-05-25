package br.com.alura.comex.processador;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.alura.comex.model.Pedido;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ProcessadorDeXML extends ProcessadorJackson {

	private static final String ARQUIVO_XML = "pedidos.xml";
	@Override
	public ObjectMapper getMapper() {
		return new XmlMapper();
	}
	@Override
	public String getNomeArquivo() {
		return ARQUIVO_XML;
	}
}
