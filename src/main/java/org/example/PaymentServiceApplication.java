package org.example;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Arrays;

@SpringBootApplication
@EnableKafka
public class PaymentServiceApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                SpringApplication.run(PaymentServiceApplication.class, args);

        Arrays.stream(context.getBeanDefinitionNames())
                .filter(name -> name.toLowerCase().contains("order"))
                .forEach(System.out::println);
    }
}
