package com.vmantek.chimera.demo.tm;

import com.vmantek.chimera.tm.VTxnSupport;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.transaction.AbortParticipant;
import org.jpos.transaction.Context;

import java.io.IOException;

@SuppressWarnings({
    "SpringAutowiredFieldsWarningInspection",
    "SpringJavaAutowiredMembersInspection"})
public class SendResponse extends VTxnSupport implements AbortParticipant
{
    @Override
    protected int doPrepare(long id, Context ctx) throws Exception
    {

        send(ctx,"00");
        return PREPARED;
    }

    @Override
    protected int doPrepareForAbort(long id, Context ctx) throws Exception
    {
        send(ctx,"99");
        return PREPARED;
    }

    private void send(Context ctx, String rc) throws IOException, ISOException
    {
        ISOMsg req = (ISOMsg) ctx.get("REQ");
        ISOSource src = (ISOSource) ctx.get("SOURCE");
        ISOMsg rsp = (ISOMsg) req.clone();
        rsp.setResponseMTI();
        rsp.set(39,rc);
        ctx.put("RSP",rsp);
        if(src.isConnected()) src.send(rsp);
    }
}
