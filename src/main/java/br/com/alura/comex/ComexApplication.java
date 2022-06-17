package br.com.alura.comex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ComexApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComexApplication.class, args);
    }

}
