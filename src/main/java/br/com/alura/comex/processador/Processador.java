package br.com.alura.comex.processador;

import br.com.alura.comex.model.Pedido;

import java.util.List;

public interface Processador {
    List<Pedido> lerRegistros() throws Exception;

}