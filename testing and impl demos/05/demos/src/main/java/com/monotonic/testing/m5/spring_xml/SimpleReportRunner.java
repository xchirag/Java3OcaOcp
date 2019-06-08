package com.monotonic.testing.m5.spring_xml;

public class SimpleReportRunner {

    public static void main(String[] args) {
        CsvSalesRepository repo = new CsvSalesRepository(args[0]);
        repo.setError(System.err);

        SalesAnalysisService analysisService = new SalesAnalysisService(repo);
        SalesReport report = new SalesReport(System.out, analysisService);
        report.report();
    }

}
