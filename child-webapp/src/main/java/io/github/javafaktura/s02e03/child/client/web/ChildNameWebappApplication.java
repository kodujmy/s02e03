package io.github.javafaktura.s02e03.child.client.web;

import io.github.javafaktura.s02e03.child.core.service.ChildNameMemoryService;
import io.github.javafaktura.s02e03.child.core.provider.ChildNameStatsCsvStatsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChildNameWebappApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChildNameWebappApplication.class, args);
    }

    @Bean
    public ChildNameMemoryService getChooser(@Value("${csv.name}") String csvName) {
        return new ChildNameMemoryService(new ChildNameStatsCsvStatsProvider(csvName));
    }
}
