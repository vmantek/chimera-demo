package com.vmantek.chimera.demo;

import com.vmantek.chimera.JPosApplication;
import com.vmantek.chimera.q2.EnableQ2;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
@EnableQ2
public class Main extends JPosApplication
{
    public static void main(String[] args)
    {
        System.setProperty("java.net.preferIPv4Stack", "true");
        System.setProperty("jdk.tls.ephemeralDHKeySize", "2048");
        System.setProperty("jdk.tls.rejectClientInitiatedRenegotiation", "true");
        System.setProperty("jsse.enableCBCProtection", "true");
        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "false");
        System.setProperty("sun.security.ssl.allowLegacyHelloMessages", "false");
        System.setProperty("spring.config.location", "file:cfg/");
        ensureDirsExists("./log", "./db", "./cfg");

        run(Main.class, args);
    }
}
