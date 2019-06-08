package com.monotonic.collections._5_queues.b_priority_queues;

import com.monotonic.collections._5_queues.Category;
import com.monotonic.collections._5_queues.Customer;
import com.monotonic.collections._5_queues.Enquiry;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.util.Comparator.comparing;

public class PriorityHelpDeskJava8
{
    private static final Comparator<Enquiry> BY_CATEGORY = comparing(Enquiry::getCategory);

    private Queue<Enquiry> enquiries = new PriorityQueue<>(BY_CATEGORY);

    public boolean enquire(final Customer customer, final Category type)
    {
        return enquiries.offer(new Enquiry(customer, type));
    }

    public void processAllEnquires()
    {
        Enquiry enquiry;
        while ((enquiry = enquiries.poll()) != null)
        {
            enquiry.getCustomer().reply("Have you tried turning it off and on again?");
        }
    }

    public static void main(String[] args)
    {
        PriorityHelpDeskJava8 helpDesk = new PriorityHelpDeskJava8();

        helpDesk.enquire(Customer.JACK, Category.PHONE);
        helpDesk.enquire(Customer.JILL, Category.PRINTER);
        helpDesk.enquire(Customer.MARY, Category.COMPUTER);

        helpDesk.processAllEnquires();
    }
}
