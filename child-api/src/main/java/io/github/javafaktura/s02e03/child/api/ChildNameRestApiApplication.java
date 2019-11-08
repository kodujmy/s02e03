package io.github.javafaktura.s02e03.child.api;

import io.github.javafaktura.s02e03.child.core.provider.ChildNameHistoricalStatsCsvProvider;
import io.github.javafaktura.s02e03.child.core.service.ChildNameMemoryService;
import io.github.javafaktura.s02e03.child.core.provider.ChildNameStatsCsvProvider;
import io.github.javafaktura.s02e03.child.core.service.ChildNameService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChildNameRestApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChildNameRestApiApplication.class, args);
    }

    @Bean
    public ChildNameService getChildNameService(@Value("${current.year.csv.name}") String currentYearCsv,
                                                @Value("${historical.csv.name}") String historicalCsv) {
        return new ChildNameMemoryService(
                new ChildNameStatsCsvProvider(currentYearCsv),
                new ChildNameHistoricalStatsCsvProvider(historicalCsv)
            );
    }
}
