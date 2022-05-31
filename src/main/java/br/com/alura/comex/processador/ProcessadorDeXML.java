package br.com.alura.comex.processador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ProcessadorDeXML extends ProcessadorJackson {

    private String arquivo = "";

    @Override
    public ObjectMapper getMapper() {
        return new XmlMapper();
    }

    @Override
    public String getNomeArquivo() {
        return arquivo;
    }

    public ProcessadorDeXML(String arquivo) {
        this.arquivo = arquivo;
    }
}
