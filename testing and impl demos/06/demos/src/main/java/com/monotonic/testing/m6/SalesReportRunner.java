package com.monotonic.testing.m6;

import java.io.PrintStream;

public class SalesReportRunner
{
    public static void main(String[] args)
    {
        run(System.out, args);
    }

    public static void run(PrintStream output, String[] arguments)
    {
        String fileLocation = arguments[0];
        SalesRepository repo = new SalesRepository(fileLocation);
        SalesAnalyser analyser = new SalesAnalyser(repo);
        SalesReport report = new SalesReport(output, analyser);
        report.run();
    }
}
