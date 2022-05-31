package br.com.alura.comex.processador;

import br.com.alura.comex.model.Pedido;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public abstract class ProcessadorJackson implements Processador {
    @Override
    public List<Pedido> lerRegistros() throws URISyntaxException, IOException {
        URL recurso = ClassLoader.getSystemResource(getNomeArquivo());
        FileReader reader = new FileReader(recurso.toURI().getPath());
        ObjectMapper objectMapper = getMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(reader, new TypeReference<List<Pedido>>() {
        });
    }

    public abstract ObjectMapper getMapper();

    public abstract String getNomeArquivo();

}
