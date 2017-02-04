package com.vmantek.chimera.demo.controller;

import com.vmantek.chimera.demo.domain.AppStats;
import com.vmantek.chimera.demo.tm.LocalISOSource;
import org.jpos.iso.ISOMsg;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Transactional
public class SampleController
{
    @RequestMapping(value = "/tm", method = RequestMethod.GET)
    public ResponseEntity<?> tm()
    {
        Space sp = SpaceFactory.getSpace();

        LocalISOSource source=new LocalISOSource();
        Context ctx = new Context();
        ISOMsg req=new ISOMsg("2800");
        ctx.put("REQ",req);
        ctx.put("SOURCE",source);
        sp.out("DEFAULT_TXN", ctx);
        ISOMsg rsp=source.getResponse(60000);

        AppStats stats=new AppStats();
        stats.setResponseCode("NR");
        if(rsp!=null)
        {
            if(rsp.hasField(39)) stats.setResponseCode(rsp.getString(39));
            stats.setCustomerCount((Long) ctx.get("customer-count"));
            stats.setPartnerCount((Long) ctx.get("partner-count"));
        }
        return ResponseEntity.ok(stats);
    }
}
