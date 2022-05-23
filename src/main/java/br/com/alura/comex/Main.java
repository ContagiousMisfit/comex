package br.com.alura.comex;

import java.util.List;

import br.com.alura.comex.menu.MenuRelatorio;
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

		MenuRelatorio menu = new MenuRelatorio();
		menu.executar();

    }
}
