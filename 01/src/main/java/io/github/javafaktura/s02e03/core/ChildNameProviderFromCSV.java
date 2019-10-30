package io.github.javafaktura.s02e03.core;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ChildNameProviderFromCSV implements ChildNameProvider{
    private final Logger logger = LoggerFactory.getLogger(ChildNameProviderFromCSV.class);
    private final String path;

    public ChildNameProviderFromCSV(String path) {
        this.path = path;
    }

    @Override
    public List<ChildName> load() {
        List<ChildName> records = new ArrayList<ChildName>();
        try (CSVReader csvReader = new CSVReader(new FileReader(path), ',', '"', 1)) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(new ChildName(values[0], Integer.valueOf(values[1]), Gender.fromSymbol(values[2])));
            }
        } catch (FileNotFoundException e) {
            logger.error("Can't read such file {}, {}", path);
        } catch (IOException e) {
            logger.error("Problem during file {} reading, {}", path);
        }
        return records;
    }
}
