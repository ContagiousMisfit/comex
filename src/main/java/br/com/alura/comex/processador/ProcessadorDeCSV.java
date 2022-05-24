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

	static final String ARQUIVO_CSV = "pedidos.csv";

	@Override
	public List<Pedido> lerRegistros() throws FileNotFoundException, URISyntaxException {

		URL recursoCSV = ClassLoader.getSystemResource(ARQUIVO_CSV);
		FileReader reader = new FileReader(recursoCSV.toURI().getPath());
				CsvToBean<Pedido> csvToBean = new CsvToBeanBuilder<Pedido>(reader).withType(Pedido.class).withSeparator(',')
				.build();
		return csvToBean.parse();

	}

}
