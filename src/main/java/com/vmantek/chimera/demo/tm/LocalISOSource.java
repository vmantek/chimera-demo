package com.vmantek.chimera.demo.tm;


import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;

public class LocalISOSource implements ISOSource
{
    private ISOMsg resp = null;

    public void send(ISOMsg m)
    {
        synchronized (this)
        {
            resp = m;
            this.notify();
        }
    }

    public boolean isConnected()
    {
        return true;
    }

    public ISOMsg getResponse(long timeout)
    {
        synchronized (this)
        {
            try
            {
                this.wait(timeout);
            }
            catch (InterruptedException ignored)
            {
            }
        }
        return resp;
    }
}
