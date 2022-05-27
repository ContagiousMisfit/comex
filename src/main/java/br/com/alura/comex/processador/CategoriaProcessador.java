package br.com.alura.comex.processador;

public enum CategoriaProcessador {
    CSV(new ProcessadorDeCSV("pedidos.csv")), JSON(new ProcessadorDeJSON("pedidos.json")), XML(new ProcessadorDeXML("pedidos.xml"));
    private final Processador processador;
    CategoriaProcessador(Processador processador) {
        this.processador = processador;
    }
    public Processador getProcessador() {
        return processador;
    }
}
