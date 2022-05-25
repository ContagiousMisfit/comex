package br.com.alura.comex.processador;

public enum CategoriaProcessador {
    CSV(new ProcessadorDeCSV()), JSON(new ProcessadorDeJSON()), XML(new ProcessadorDeXML());
    private final Processador processador;
    CategoriaProcessador(Processador processador) {
        this.processador = processador;
    }
    public Processador getProcessador() {
        return processador;
    }
}
