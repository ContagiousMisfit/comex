package br.com.alura.comex.loja.api.model.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErroDeFormularioDto {

    private String campo;
    private String erro;

}
