package com.vmantek.chimera.demo.tm;

import com.vmantek.chimera.demo.domain.Customer;
import com.vmantek.chimera.demo.repos.CustomerRepository;
import com.vmantek.chimera.tm.VTxnSupport;
import org.jpos.transaction.Context;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings({
    "SpringAutowiredFieldsWarningInspection",
    "SpringJavaAutowiredMembersInspection"})
public class SampleParticipant extends VTxnSupport
{
    @Autowired
    CustomerRepository customerRepository;

    @Override
    protected int doPrepare(long id, Context ctx) throws Exception
    {
        // Using Repositories
        customerRepository.save(new Customer("Vlad the Impaler", "Real bad guy"));

        // Using typical jPOS convention
        getDB(ctx).session().save(new Customer("Gorn", "Kirk's Nemesis"));

        return PREPARED | READONLY | NO_JOIN;
    }

    public void setCustomerRepository(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }
}
