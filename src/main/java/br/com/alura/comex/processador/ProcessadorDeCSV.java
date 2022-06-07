package br.com.alura.comex.processador;

import br.com.alura.comex.dto.PedidoDTO;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ProcessadorDeCSV {

    private String arquivo = "pedidos.csv";

    public List<PedidoDTO> lerRegistros() throws FileNotFoundException, URISyntaxException {
        URL recursoCSV = ClassLoader.getSystemResource(arquivo);
        FileReader reader = new FileReader(recursoCSV.toURI().getPath());
        CsvToBean<PedidoDTO> csvToBean = new CsvToBeanBuilder<PedidoDTO>(reader).withType(PedidoDTO.class).withSeparator(',')
                .build();

        List<PedidoDTO> listaDePedidos = csvToBean.parse();
        return listaDePedidos;
    }

}
