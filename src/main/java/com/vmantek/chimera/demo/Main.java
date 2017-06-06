package com.vmantek.chimera.demo;

import com.vmantek.chimera.JPosApplication;
import io.prometheus.client.Counter;
import io.prometheus.client.SimpleCollector;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public class Main extends JPosApplication
{
    public static void main(String[] args)
    {
        run(Main.class, args);
    }
}
