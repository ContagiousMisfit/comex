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
import br.com.alura.comex.utils.ProcessadorDeXML;

public class Main {


    public static void main(String[] args) throws Exception {
        
    	ProcessadorDeCSV processadorDeCSV = new ProcessadorDeCSV();
    	List<Pedido> listaDePedidos = processadorDeCSV.lerRegistros();
    	
    	Processador processadorDeJSON = new ProcessadorDeJSON();
    	List<Pedido> listaDePedidosJSON = processadorDeJSON.lerRegistros();

    	Processador processadorDeXML = new ProcessadorDeXML();
    	List<Pedido> listaDePedidosXML = processadorDeXML.lerRegistros();
    	
    	Relatorio relatorioSintetico = new RelatorioSintetico();
    	relatorioSintetico.filtrarRelatorio(listaDePedidosXML);
    	relatorioSintetico.imprimirRelatorio();

        Relatorio relatorioFidelidade = new RelatorioFidelidade();
        relatorioFidelidade.filtrarRelatorio(listaDePedidosXML);
        relatorioFidelidade.imprimirRelatorio();
        
        Relatorio relatorioVendasPorCategoria = new RelatorioVendasPorCategoria();
        relatorioVendasPorCategoria.filtrarRelatorio(listaDePedidosXML);
        relatorioVendasPorCategoria.imprimirRelatorio();
        
        Relatorio relatorioProdutosMaisVendidos = new RelatorioProdutosMaisVendidos();
        relatorioProdutosMaisVendidos.filtrarRelatorio(listaDePedidosXML);
        relatorioProdutosMaisVendidos.imprimirRelatorio();
        
        Relatorio relatorioProdutosMaisCaros = new RelatorioProdutosMaisCaros();
        relatorioProdutosMaisCaros.filtrarRelatorio(listaDePedidosXML);
        relatorioProdutosMaisCaros.imprimirRelatorio();
        
        Relatorio relatorioClientesMaisLucrativos = new RelatorioClientesMaisLucrativos();
        relatorioClientesMaisLucrativos.filtrarRelatorio(listaDePedidosXML);
        relatorioClientesMaisLucrativos.imprimirRelatorio();
        
    }
}
