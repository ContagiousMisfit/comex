package br.com.alura.comex.relatorios;

public class RelatorioProxy extends Relatorio{

    private Relatorio relatorio;
    public RelatorioProxy(Relatorio relatorio) {
        super();
        this.relatorio = relatorio;
    }

    @Override
    public void filtrarRelatorio() {
    }

    @Override
    public void imprimirRelatorio() {

    }

    @Override
    public void executa() {
        relatorio.executa();
    }

}
