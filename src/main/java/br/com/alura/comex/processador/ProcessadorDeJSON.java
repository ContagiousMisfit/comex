package br.com.alura.comex.processador;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProcessadorDeJSON extends ProcessadorJackson {

    private String arquivo = "";

    @Override
    public ObjectMapper getMapper() {
        return new ObjectMapper();
    }

    @Override
    public String getNomeArquivo() {
        return arquivo;
    }

    public ProcessadorDeJSON(String arquivo) {
        this.arquivo = arquivo;
    }
}