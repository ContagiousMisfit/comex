package br.com.alura.comex.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoDTO {

    @CsvBindByName(column = "CATEGORIA", required = true)
    private String categoria;

    @CsvBindByName(column = "PRODUTO", required = true)
    private String produto;

    @CsvBindByName(column = "PRECO", required = true)
    private BigDecimal precoUnitario;

    @CsvBindByName(column = "QUANTIDADE", required = true)
    private long quantidade;

    @CsvBindByName(column = "DATA", required = true)
    private LocalDateTime data;

    @CsvBindByName(column = "CLIENTE", required = true)
    private String cliente;

}
