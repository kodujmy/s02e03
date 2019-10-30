package io.github.javafaktura.s02e03;

import io.github.javafaktura.s02e03.core.ChildNameChooser;
import io.github.javafaktura.s02e03.core.ChildNameProviderFromCSV;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChildNameApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChildNameApplication.class, args);
    }

    @Bean
    public ChildNameChooser getChooser(@Value("${csv.name}") String csvName) {
        return new ChildNameChooser(new ChildNameProviderFromCSV(getPathToFile(csvName)));
    }

    private String getPathToFile(String fileName) {
        return getClass().getClassLoader().getResource(fileName).getPath();
    }
}
