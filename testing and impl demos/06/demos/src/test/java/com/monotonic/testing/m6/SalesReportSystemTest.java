package com.monotonic.testing.m6;

import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class SalesReportSystemTest
{

    @Test
    public void findsExampleSalesInOutput()
    {
        ApplicationRunner runner = new ApplicationRunner();

        String output = runner.run("src/main/resources/example-sales.csv");

        assertThat(output, containsString("- London          -    235 -"));
    }

}
