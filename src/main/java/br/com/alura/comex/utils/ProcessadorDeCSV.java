package br.com.alura.comex.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.alura.comex.Pedido;

public class ProcessadorDeCSV {

	private static final String ARQUIVO_CSV = "C:\\Users\\Darkn\\Desktop\\CI&T\\comex\\src\\main\\resources\\pedidos.csv";

	public List<Pedido> lerRegistros() throws FileNotFoundException {

		FileReader reader = new FileReader(ARQUIVO_CSV);
		CsvToBean<Pedido> csvToBean = new CsvToBeanBuilder<Pedido>(reader)
				.withType(Pedido.class)
				.withSeparator(',')
				.build();
		return csvToBean.parse();

	}

}
