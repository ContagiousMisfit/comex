package br.com.alura.comex.loja.api.model;

import br.com.alura.comex.loja.domain.Endereco;
import lombok.Data;

import java.util.List;

@Data
public class EnderecoDto {

    private final String rua;
    private final String numero;
    private final String complemento;
    private final String bairro;
    private final String cidade;
    private final String estado;

    public EnderecoDto(Endereco endereco) {
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
    }

    public static List<EnderecoDto> converter(List<Endereco> enderecos) {
        return enderecos.stream().map(EnderecoDto::new).toList();
    }


}
