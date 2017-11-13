package com.vmantek.chimera.demo.tm;

import com.vmantek.chimera.demo.domain.Customer;
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
public class SampleParticipant extends VTxnSupport
{
    @Setter @NonNull
    private CustomerRepository customerRepository;

    @Override
    protected int doPrepare(long id, Context ctx) throws Exception
    {
        // Using Repositories
        customerRepository.save(new Customer("Vlad the Impaler", "Real bad guy"));

        // Using typical jPOS convention
        getDB(ctx).session().save(new Customer("Gorn", "Kirk's Nemesis"));

        return PREPARED | READONLY | NO_JOIN;
    }
}
