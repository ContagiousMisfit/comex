package br.com.alura.comex.relatorios;

import br.com.alura.comex.model.Pedido;

import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RelatorioFidelidade extends Relatorio {

    private List<ClientesFieis> clientesFieis;
    private final Consumer<String> impressoraDoRelatorio;
    public List<RelatorioFidelidade.ClientesFieis> getClientesFieis() {
        return clientesFieis;
    }
    public RelatorioFidelidade(List<Pedido> listaDePedidos, Consumer<String> impressoraDoRelatorio) {
        super(listaDePedidos);
        this.impressoraDoRelatorio = impressoraDoRelatorio;
    }
	@Override
    public void filtrarRelatorio() {
        if (listaDePedidos == null)
            throw new IllegalArgumentException("A lista de pedidos de um relatório não pode ser nula!");
        if (listaDePedidos.isEmpty())
            throw new IllegalArgumentException("A lista não pode estar vazia!");
        clientesFieis = listaDePedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCliente, TreeMap::new, Collectors.counting()))
                .entrySet().stream()
                .map(entry -> {
                    String cliente = entry.getKey();
                    Long numPedidos = entry.getValue();
                    return new ClientesFieis(cliente, numPedidos);
                })
                .toList();
    }

    @Override
    public void imprimirRelatorio() {
        impressoraDoRelatorio.accept("\n#### RELATÓRIO DE CLIENTES FIÉIS");
        clientesFieis.forEach(cliente -> {
            impressoraDoRelatorio.accept("NOME: " + cliente.getCliente() + "\nNº DE PEDIDOS: " + cliente.getNumPedidos() + "\n");
        });
    }

    public class ClientesFieis {
        private final String cliente;
        private final Long numPedidos;

        public ClientesFieis(String cliente, Long numPedidos) {
            this.cliente = cliente;
            this.numPedidos = numPedidos;
        }

        public String getCliente() {
            return cliente;
        }

        public Long getNumPedidos() {
            return numPedidos;
        }
    }

}
