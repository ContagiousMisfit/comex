package br.com.alura.comex.vo;

import br.com.alura.comex.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioClientesMaisLucrativosVo {

    private List<Cliente> clientesMaisLucrativos;

}
