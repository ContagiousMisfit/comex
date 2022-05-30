package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;

import java.util.List;
import java.util.function.Function;

public enum CategoriaRelatorio {
    SINTETICO(pedidos -> new RelatorioProxy(new RelatorioSintetico(pedidos))),
    CLIENTES_FIEIS(pedidos -> new RelatorioProxy(new RelatorioFidelidade(pedidos))),
    VENDAS_POR_CATEGORIA(pedidos -> new RelatorioProxy(new RelatorioVendasPorCategoria(pedidos, relatorio -> {
        System.out.println(relatorio);
    }))),
    PRODUTOS_MAIS_VENDIDOS(pedidos -> new RelatorioProxy(new RelatorioProdutosMaisVendidos(pedidos))),
    PRODUTOS_MAIS_CAROS(pedidos -> new RelatorioProxy(new RelatorioProdutosMaisCaros(pedidos))),
    CLIENTES_MAIS_LUCRATIVOS(pedidos -> new RelatorioProxy(new RelatorioClientesMaisLucrativos(pedidos)));

    private final Function<List<Pedido>,Relatorio> relatorioSupplier;

    CategoriaRelatorio(Function<List<Pedido>,Relatorio> relatorioSupplier) {
        this.relatorioSupplier = relatorioSupplier;
    }

    public Relatorio getRelatorio(List<Pedido> listaDePedidos) {
        return relatorioSupplier.apply(listaDePedidos);
    }

}
