package com.tcc.oficina_app;

import com.tcc.oficina_app.model.Servico;
import com.tcc.oficina_app.model.ServicoSubServico;
import com.tcc.oficina_app.services.ServicoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.HashSet;


@SpringBootApplication
public class OficinaAppApplication {


    public static void main(String[] args) {
        SpringApplication.run(OficinaAppApplication.class, args);
    }


}
