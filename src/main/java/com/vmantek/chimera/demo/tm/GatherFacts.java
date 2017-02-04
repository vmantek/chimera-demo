package com.vmantek.chimera.demo.tm;

import com.vmantek.chimera.tm.VTxnSupport;
import com.vmantek.chimera.demo.repos.CustomerRepository;
import org.jpos.transaction.Context;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings({
    "SpringAutowiredFieldsWarningInspection",
    "SpringJavaAutowiredMembersInspection"})
public class GatherFacts extends VTxnSupport
{
    @Autowired
    CustomerRepository customerRepository;

    public void setCustomerRepository(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    @Override
    protected int doPrepare(long id, Context ctx) throws Exception
    {
        ctx.put("customer-count", customerRepository.count());
        return PREPARED | NO_JOIN;
    }
}
