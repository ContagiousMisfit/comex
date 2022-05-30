package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.utils.Formatador;

import java.util.List;
import java.util.function.Consumer;

public class RelatorioSintetico extends Relatorio {

    private final Consumer<String> impressoraDoRelatorio;
    public RelatorioSintetico(List<Pedido> listaDePedidos, Consumer<String> impressoraDoRelatorio) {
        super(listaDePedidos);
        this.impressoraDoRelatorio = impressoraDoRelatorio;
    }

    @Override
    public void filtrarRelatorio() {

    }

    @Override
    public void imprimirRelatorio() {

        impressoraDoRelatorio.accept("#### RELATÓRIO DE VALORES TOTAIS" + "\n- TOTAL DE PEDIDOS REALIZADOS: "
                + getTotalDePedidosRealizados() + "\n- TOTAL DE PRODUTOS VENDIDOS: " + getTotalDeProdutosVendidos()
                + "\n- TOTAL DE CATEGORIAS: " + getTotalDeCategorias()
                + "\n- MONTANTE DE VENDAS: " + Formatador.formatarValorTotal(getMontanteDeVendas())
                + "\n- PEDIDO MAIS BARATO: " + Formatador.formatarValorTotal(getPedidoMaisBarato().getValorTotal()) + " " + getPedidoMaisBarato().getProduto()
                + "\n- PEDIDO MAIS CARO: "+ Formatador.formatarValorTotal(getPedidoMaisCaro().getValorTotal()) + " " + getPedidoMaisCaro().getProduto());
    }

}
