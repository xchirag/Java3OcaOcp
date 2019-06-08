package com.monotonic.testing.m5.before_refactor;

import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class SalesAnalysisService {

    private final String fileLocation;

    public SalesAnalysisService(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Map<String, Integer> tallyProductSales() {
        return tallySalesBy(Sale::getProduct);
    }

    public Map<String, Integer> tallyStoreSales() {
        return tallySalesBy(Sale::getStore);
    }

    private Map<String, Integer> tallySalesBy(Function<Sale, String> classifier) {
        CsvSalesRepository repo = new CsvSalesRepository(fileLocation);
        return repo.loadSales()
                   .stream()
                   .collect(groupingBy(classifier,
                                summingInt(Sale::getValue)));
    }

}
