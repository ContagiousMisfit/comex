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
import br.com.alura.comex.utils.Processador;
import br.com.alura.comex.utils.ProcessadorDeCSV;
import br.com.alura.comex.utils.ProcessadorDeJSON;

public class Main {


    public static void main(String[] args) throws Exception {
        
    	ProcessadorDeCSV processadorDeCSV = new ProcessadorDeCSV();
    	List<Pedido> listaDePedidos = processadorDeCSV.lerRegistros();
    	
    	Processador processadorDeJSON = new ProcessadorDeJSON();
    	List<Pedido> listaDePedidosJSON = processadorDeJSON.lerRegistros();

    	Relatorio relatorioSintetico = new RelatorioSintetico();
    	relatorioSintetico.filtrarRelatorio(listaDePedidosJSON);
    	relatorioSintetico.imprimirRelatorio();

        Relatorio relatorioFidelidade = new RelatorioFidelidade();
        relatorioFidelidade.filtrarRelatorio(listaDePedidosJSON);
        relatorioFidelidade.imprimirRelatorio();
        
        Relatorio relatorioVendasPorCategoria = new RelatorioVendasPorCategoria();
        relatorioVendasPorCategoria.filtrarRelatorio(listaDePedidosJSON);
        relatorioVendasPorCategoria.imprimirRelatorio();
        
        Relatorio relatorioProdutosMaisVendidos = new RelatorioProdutosMaisVendidos();
        relatorioProdutosMaisVendidos.filtrarRelatorio(listaDePedidosJSON);
        relatorioProdutosMaisVendidos.imprimirRelatorio();
        
        Relatorio relatorioProdutosMaisCaros = new RelatorioProdutosMaisCaros();
        relatorioProdutosMaisCaros.filtrarRelatorio(listaDePedidosJSON);
        relatorioProdutosMaisCaros.imprimirRelatorio();
        
        Relatorio relatorioClientesMaisLucrativos = new RelatorioClientesMaisLucrativos();
        relatorioClientesMaisLucrativos.filtrarRelatorio(listaDePedidosJSON);
        relatorioClientesMaisLucrativos.imprimirRelatorio();
        
    }
}
