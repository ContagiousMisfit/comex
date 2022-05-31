package br.com.alura.comex.processador;

import br.com.alura.comex.model.Pedido;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class ProcessadorDeCSV implements Processador {

    private String arquivo = "";

    public ProcessadorDeCSV(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public List<Pedido> lerRegistros() throws FileNotFoundException, URISyntaxException {
        URL recursoCSV = ClassLoader.getSystemResource(arquivo);
        FileReader reader = new FileReader(recursoCSV.toURI().getPath());
        CsvToBean<Pedido> csvToBean = new CsvToBeanBuilder<Pedido>(reader).withType(Pedido.class).withSeparator(',')
                .build();

        List<Pedido> listaDePedidos = csvToBean.parse();
        return listaDePedidos;
    }

}
