package br.com.alura.comex;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import br.com.alura.comex.relatorios.Relatorio;
import br.com.alura.comex.relatorios.RelatorioClientesMaisLucrativos;
import br.com.alura.comex.relatorios.RelatorioFidelidade;
import br.com.alura.comex.relatorios.RelatorioProdutosMaisCaros;
import br.com.alura.comex.relatorios.RelatorioProdutosMaisVendidos;
import br.com.alura.comex.relatorios.RelatorioSintetico;
import br.com.alura.comex.relatorios.RelatorioVendasPorCategoria;
import br.com.alura.comex.utils.ProcessadorDeCSV;

public class Main {


    public static void main(String[] args) throws IOException, URISyntaxException {
        
    	ProcessadorDeCSV processadorDeCSV = new ProcessadorDeCSV();
    	List<Pedido> listaDePedidos = processadorDeCSV.lerRegistros();

    	Relatorio relatorioSintetico = new RelatorioSintetico();
    	relatorioSintetico.filtrarRelatorio(listaDePedidos);
    	relatorioSintetico.imprimirRelatorio();

        Relatorio relatorioFidelidade = new RelatorioFidelidade();
        relatorioFidelidade.filtrarRelatorio(listaDePedidos);
        relatorioFidelidade.imprimirRelatorio();
        
        Relatorio relatorioVendasPorCategoria = new RelatorioVendasPorCategoria();
        relatorioVendasPorCategoria.filtrarRelatorio(listaDePedidos);
        relatorioVendasPorCategoria.imprimirRelatorio();
        
        Relatorio relatorioProdutosMaisVendidos = new RelatorioProdutosMaisVendidos();
        relatorioProdutosMaisVendidos.filtrarRelatorio(listaDePedidos);
        relatorioProdutosMaisVendidos.imprimirRelatorio();
        
        Relatorio relatorioProdutosMaisCaros = new RelatorioProdutosMaisCaros();
        relatorioProdutosMaisCaros.filtrarRelatorio(listaDePedidos);
        relatorioProdutosMaisCaros.imprimirRelatorio();
        
        Relatorio relatorioClientesMaisLucrativos = new RelatorioClientesMaisLucrativos();
        relatorioClientesMaisLucrativos.filtrarRelatorio(listaDePedidos);
        relatorioClientesMaisLucrativos.imprimirRelatorio();
        
    }
}
