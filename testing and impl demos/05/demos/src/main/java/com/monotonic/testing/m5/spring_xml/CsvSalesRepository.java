package com.monotonic.testing.m5.spring_xml;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvSalesRepository implements SalesRepository {

    private final String fileLocation;
    private PrintStream error;
    private List<Sale> sales;

    @Autowired
    @Inject
    public CsvSalesRepository(@Named("fileLocation") @Qualifier("fileLocation") String fileLocation) {
        this.fileLocation = fileLocation;
        error = System.out;
    }

    public void setError(PrintStream error) {
        this.error = error;
    }

    private int parseInt(String value) {
        return Integer.parseInt(value.trim());
    }

    @Override
    public List<Sale> loadSales() {
        if (sales == null) {
            sales = new ArrayList<>();
            try (CSVReader reader = new CSVReader(new FileReader(fileLocation))) {
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    String product = nextLine[0].trim();
                    String store = nextLine[1].trim();
                    int number = parseInt(nextLine[2]);
                    int pricePerItem = parseInt(nextLine[3]);
                    sales.add(new Sale(product, store, number, pricePerItem));
                }
                return sales;
            } catch (IOException e) {
                e.printStackTrace(error);
                return null;
            }
        }
        return sales;
    }

}
