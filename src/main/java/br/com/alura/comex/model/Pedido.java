package br.com.alura.comex.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Pedido {

    @CsvBindByName(column = "CATEGORIA", required = true)
    private String categoria;
    @CsvBindByName(column = "PRODUTO", required = true)
    private String produto;
    @CsvBindByName(column = "PRECO", required = true)
    private BigDecimal preco;
    @CsvBindByName(column = "QUANTIDADE", required = true)
    private int quantidade;
    @CsvBindByName(column = "DATA", required = true)
    @CsvDate(value = "dd/MM/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate data;
    @CsvBindByName(column = "CLIENTE", required = true)
    private String cliente;

    public Pedido() {
        super();
    }

    public Pedido(String categoria, String produto, String cliente, BigDecimal preco, int quantidade, LocalDate data) {
        this.categoria = categoria;
        this.produto = produto;
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProduto() {
        return produto;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Pedido{" + "categoria='" + categoria + '\'' + ", produto='" + produto + '\'' + ", cliente='" + cliente
                + '\'' + ", preco=" + preco + ", quantidade=" + quantidade + ", data=" + data + '}';
    }

    public BigDecimal getValorTotal() {
        return getPreco().multiply(new BigDecimal(getQuantidade()));
    }

    public static BigDecimal getMontanteCliente(Map.Entry<String, List<Pedido>> cliente) {
        return cliente.getValue().stream().map(pedido -> pedido.getValorTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}