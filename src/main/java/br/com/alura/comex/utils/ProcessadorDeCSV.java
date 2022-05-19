package br.com.alura.comex.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.alura.comex.Pedido;

public class ProcessadorDeCSV implements Processador{

	static final String arquivoCSV = "C:\\Users\\Darkn\\Desktop\\CI&T\\comex\\src\\main\\resources\\pedidos.csv";
	
	@Override
	public List<Pedido> lerRegistros() throws FileNotFoundException {

		FileReader reader = new FileReader(arquivoCSV);
		CsvToBean<Pedido> csvToBean = new CsvToBeanBuilder<Pedido>(reader)
				.withType(Pedido.class)
				.withSeparator(',')
				.build();
		return csvToBean.parse();

	}

}
