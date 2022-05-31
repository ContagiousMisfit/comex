package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;

import java.util.List;
import java.util.function.Function;

public enum CategoriaRelatorio {
    SINTETICO(pedidos -> new RelatorioProxy(new RelatorioSintetico(pedidos, relatorio -> {
        System.out.println(relatorio);
    }))),
    CLIENTES_FIEIS(pedidos -> new RelatorioProxy(new RelatorioFidelidade(pedidos, relatorio -> {
        System.out.println(relatorio);
    }))),
    VENDAS_POR_CATEGORIA(pedidos -> new RelatorioProxy(new RelatorioVendasPorCategoria(pedidos, relatorio -> {
        System.out.println(relatorio);
    }))),
    PRODUTOS_MAIS_VENDIDOS(pedidos -> new RelatorioProxy(new RelatorioProdutosMaisVendidos(pedidos, relatorio -> {
        System.out.println(relatorio);
    }))),
    PRODUTOS_MAIS_CAROS(pedidos -> new RelatorioProxy(new RelatorioProdutosMaisCaros(pedidos, relatorio -> {
        System.out.println(relatorio);
    }))),
    CLIENTES_MAIS_LUCRATIVOS(pedidos -> new RelatorioProxy(new RelatorioClientesMaisLucrativos(pedidos, relatorio -> {
        System.out.println(relatorio);
    })));

    private final Function<List<Pedido>, Relatorio> relatorioSupplier;

    CategoriaRelatorio(Function<List<Pedido>, Relatorio> relatorioSupplier) {
        this.relatorioSupplier = relatorioSupplier;
    }

    public Relatorio getRelatorio(List<Pedido> listaDePedidos) {
        return relatorioSupplier.apply(listaDePedidos);
    }

}
