package com.monotonic.testing.m6;

import java.io.PrintStream;

/**
 * .
 */
public class SalesReport
{
    private final PrintStream output;
    private final SalesAnalyser analyser;

    public SalesReport(final PrintStream output, SalesAnalyser analyser)
    {
        this.output = output;
        this.analyser = analyser;
    }

    public void run()
    {
        analyser.tallyStoreSales().forEach((city, value) ->
        {
            output.printf("- %-15s - %6.6s -%n", city, value);
        });
    }
}
