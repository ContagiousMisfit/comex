package br.com.alura.comex.processador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.alura.comex.model.Pedido;

public class ProcessadorDeCSV implements Processador {

    private String arquivo = "";

    public ProcessadorDeCSV(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public List<Pedido> lerRegistros() throws FileNotFoundException, URISyntaxException {

        if (arquivo == null)
            throw new IllegalArgumentException("O arquivo não pode estar nulo!");

        URL recursoCSV = ClassLoader.getSystemResource(arquivo);
        FileReader reader = new FileReader(recursoCSV.toURI().getPath());
        CsvToBean<Pedido> csvToBean = new CsvToBeanBuilder<Pedido>(reader).withType(Pedido.class).withSeparator(',')
                .build();

        List<Pedido> listaDePedidos = csvToBean.parse();
		if (listaDePedidos.isEmpty() || listaDePedidos.get(1) == null)
			throw new IllegalArgumentException("O relatório não pode ser gerado com a lista vazia ou com apenas um item!");

		return listaDePedidos;
    }

}
