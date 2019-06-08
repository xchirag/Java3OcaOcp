package com.monotonic.testing.m5.spring_xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Component
public class SalesAnalysisService {

    private final SalesRepository repo;

    @Autowired
    public SalesAnalysisService(SalesRepository repo) {
        this.repo = repo;
    }

    public Map<String, Integer> tallyProductSales() {
        return tallySalesBy(Sale::getProduct);
    }

    public Map<String, Integer> tallyStoreSales() {
        return tallySalesBy(Sale::getStore);
    }

    private Map<String, Integer> tallySalesBy(Function<Sale, String> classifier) {
        return repo.loadSales()
                   .stream()
                   .collect(groupingBy(classifier,
                                summingInt(Sale::getValue)));
    }

}
