package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.utils.Formatador;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RelatorioClientesMaisLucrativos extends Relatorio {

    private final Consumer<String> impressoraDoRelatorio;
    List<ClientesMaisLucrativos> clientesMaisLucrativos;

    public RelatorioClientesMaisLucrativos(List<Pedido> listaDePedidos, Consumer<String> impressoraDoRelatorio) {
        super(listaDePedidos);
        this.impressoraDoRelatorio = impressoraDoRelatorio;
    }

    public List<RelatorioClientesMaisLucrativos.ClientesMaisLucrativos> getClientesMaisLucrativos() {
        return clientesMaisLucrativos;
    }

    @Override
    public void filtrarRelatorio() {
        if (listaDePedidos == null)
            throw new IllegalArgumentException("A lista de pedidos de um relatório não pode ser nula!");
        if (listaDePedidos.isEmpty())
            throw new IllegalArgumentException("A lista não pode estar vazia!");
        clientesMaisLucrativos = listaDePedidos.stream().collect(Collectors.groupingBy(Pedido::getCliente))
                .entrySet().stream().sorted(Map.Entry.comparingByKey())
                .map(cliente -> {
                    String nome = cliente.getKey();
                    Long numPedidos = cliente.getValue().stream().map(Pedido::getQuantidade).count();
                    BigDecimal montanteGasto = getMontanteCliente(cliente);
                    return new ClientesMaisLucrativos(nome, numPedidos, montanteGasto);
                }).toList();
    }

    @Override
    public void imprimirRelatorio() {
        impressoraDoRelatorio.accept("\n#### CLIENTES MAIS LUCRATIVOS");
        clientesMaisLucrativos.forEach(cliente -> {
                    impressoraDoRelatorio.accept("NOME: " + cliente.getNome() + "\nNº DE PEDIDOS: "
                            + cliente.getNumPedidos()
                            + "\nMONTANTE GASTO: "
                            + Formatador.formatarValorTotal(cliente.getMontanteGasto())
                            + "\n");
                });
    }

    public class ClientesMaisLucrativos {
        private final String nome;
        private final Long numPedidos;
        private final BigDecimal montanteGasto;

        public ClientesMaisLucrativos(String nome, Long numPedidos, BigDecimal montanteGasto) {
            this.nome = nome;
            this.numPedidos = numPedidos;
            this.montanteGasto = montanteGasto;
        }

        public String getNome() {
            return nome;
        }

        public Long getNumPedidos() {
            return numPedidos;
        }

        public BigDecimal getMontanteGasto() {
            return montanteGasto;
        }
    }

}
