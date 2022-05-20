package br.com.alura.comex;

import java.util.List;

import br.com.alura.comex.model.Pedido;
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
        
    	Processador processadorDeCSV = new ProcessadorDeCSV();
    	List<Pedido> listaDePedidos = processadorDeCSV.lerRegistros();
    	
    	Processador processadorDeJSON = new ProcessadorDeJSON();
    	List<Pedido> listaDePedidosJSON = processadorDeJSON.lerRegistros();

    	Processador processadorDeXML = new ProcessadorDeXML();
    	List<Pedido> listaDePedidosXML = processadorDeXML.lerRegistros();
    	
    	Relatorio relatorioSintetico = new RelatorioSintetico(listaDePedidos);
    	relatorioSintetico.executa();

    	Relatorio relatorioFidelidade = new RelatorioFidelidade(listaDePedidos);
    	relatorioFidelidade.executa();

    	Relatorio relatorioVendasPorCategoria = new RelatorioVendasPorCategoria(listaDePedidos);
    	relatorioVendasPorCategoria.executa();
      
    	Relatorio relatorioProdutosMaisVendidos = new RelatorioProdutosMaisVendidos(listaDePedidosXML);
    	relatorioProdutosMaisVendidos.executa();
      
    	Relatorio relatorioProdutosMaisCaros = new RelatorioProdutosMaisCaros(listaDePedidosXML);
    	relatorioProdutosMaisCaros.executa();
      
    	Relatorio relatorioClientesMaisLucrativos = new RelatorioClientesMaisLucrativos(listaDePedidosXML);
    	relatorioClientesMaisLucrativos.executa();
    }
}
