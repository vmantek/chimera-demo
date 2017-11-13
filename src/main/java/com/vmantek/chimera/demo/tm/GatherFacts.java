package com.vmantek.chimera.demo.tm;

import com.vmantek.chimera.demo.repos.CustomerRepository;
import com.vmantek.chimera.tm.VTxnSupport;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jpos.transaction.Context;

@SuppressWarnings({
    "SpringAutowiredFieldsWarningInspection",
    "SpringJavaAutowiredMembersInspection"})
@NoArgsConstructor
@RequiredArgsConstructor
public class GatherFacts extends VTxnSupport
{
    @Setter @NonNull
    private CustomerRepository customerRepository;

    @Override
    protected int doPrepare(long id, Context ctx) throws Exception
    {
        ctx.put("customer-count", customerRepository.count());
        return PREPARED | NO_JOIN;
    }
}
