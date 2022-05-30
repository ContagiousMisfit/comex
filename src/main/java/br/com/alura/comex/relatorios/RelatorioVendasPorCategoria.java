package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.utils.Formatador;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RelatorioVendasPorCategoria extends Relatorio {

    private final Consumer<String> impressoraDoRelatorio;
    private List<VendasPorCategoria> vendasPorCategoria;

    public RelatorioVendasPorCategoria(List<Pedido> listaDePedidos, Consumer<String> impressoraDoRelatorio) {
        super(listaDePedidos);
        this.impressoraDoRelatorio = impressoraDoRelatorio;
    }

    public List<VendasPorCategoria> getVendasPorCategoria() {
        return vendasPorCategoria;
    }

    @Override
    public void filtrarRelatorio() {
        if (listaDePedidos == null)
            throw new IllegalArgumentException("A lista de pedidos de um relatório não pode ser nula!");
        if (listaDePedidos.isEmpty())
            throw new IllegalArgumentException("A lista não pode estar vazia!");
        vendasPorCategoria = listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCategoria))
                .entrySet().stream().sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    String categoria = entry.getKey();
                    int quantidadeVendida = entry.getValue().stream().mapToInt(Pedido::getQuantidade).sum();
                    BigDecimal montanteCliente = getMontanteCliente(entry);
                    return new VendasPorCategoria(categoria, quantidadeVendida, montanteCliente);
                }).toList();
    }

    @Override
    public void imprimirRelatorio() {
        impressoraDoRelatorio.accept("\n#### RELATÓRIO DE VENDAS POR CATEGORIA");
        vendasPorCategoria.stream().forEach(vendasPorCategoria -> {
            impressoraDoRelatorio.accept("CATEGORIA: " + vendasPorCategoria.getCategoria()
                    + "\nQUANTIDADE VENDIDA: " + vendasPorCategoria.getQuantidadeVendida()
                    + "\nMONTANTE: " + Formatador.formatarValorTotal(vendasPorCategoria.getMontante())
                    + "\n");
        });

    }

    public class VendasPorCategoria {

        private final String categoria;
        private final int quantidadeVendida;
        private final BigDecimal montante;

        public VendasPorCategoria(String categoria, int quantidadeVendida, BigDecimal montante) {
            this.categoria = categoria;
            this.quantidadeVendida = quantidadeVendida;
            this.montante = montante;
        }

        public String getCategoria() {
            return categoria;
        }

        public int getQuantidadeVendida() {
            return quantidadeVendida;
        }

        public BigDecimal getMontante() {
            return montante;
        }
    }


}
