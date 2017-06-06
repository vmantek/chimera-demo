package io.prometheus.client.spring.boot;

import io.prometheus.client.Collector;
import io.prometheus.client.Collector.MetricFamilySamples.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Spring boot metrics integration for Prometheus exporter.</p>
 * <p>
 * <pre><code>{@literal @}Bean
 * public SpringBootMetricsCollector springBootMetricsCollector(Collection{@literal <}PublicMetrics{@literal >} publicMetrics) {
 *   SpringBootMetricsCollector springBootMetricsCollector = new SpringBootMetricsCollector(publicMetrics);
 *   springBootMetricsCollector.register();
 *   return springBootMetricsCollector;
 * }
 * </code></pre>
 */
@Component
@ConfigurationProperties(prefix = "prometheus.metrics-collector")
public class SpringBootMetricsCollector extends Collector implements Collector.Describable
{
    private final Collection<PublicMetrics> publicMetrics;

    String namespace = "springboot";
    String subsystem = "";

    Map<String,String> labels = new HashMap<>();

    public String getNamespace()
    {
        return namespace;
    }

    public void setNamespace(String namespace)
    {
        this.namespace = namespace;
    }

    public String getSubsystem()
    {
        return subsystem;
    }

    public void setSubsystem(String subsystem)
    {
        this.subsystem = subsystem;
    }

    public Map<String, String> getLabels()
    {
        return labels;
    }

    public void setLabels(Map<String, String> labels)
    {
        this.labels = labels;
    }

    @Autowired
    public SpringBootMetricsCollector(Collection<PublicMetrics> publicMetrics)
    {
        this.publicMetrics = publicMetrics;
    }

    @Override
    public List<MetricFamilySamples> collect()
    {
        List<String> emptyStringList = Collections.<String>emptyList();
        ArrayList<MetricFamilySamples> samples = new ArrayList<>();

        for (PublicMetrics publicMetrics : this.publicMetrics)
        {
            for (Metric<?> metric : publicMetrics.metrics())
            {
                String name = Collector.sanitizeMetricName(metric.getName());
                if (!subsystem.isEmpty())
                {
                    name = subsystem + '_' + name;
                }
                if (!namespace.isEmpty())
                {
                    name = namespace + '_' + name;
                }
                String fullname = name;
                checkMetricName(fullname);

                for (String n : labels.keySet())
                {
                    checkMetricLabelName(n);
                }

                ArrayList<String> labelNames = new ArrayList<>(labels.keySet());
                ArrayList<String> labelValues = new ArrayList<>(labels.values());
                double value = metric.getValue().doubleValue();

                Sample sample = new Sample(name, labelNames, labelValues, value);
                List<Sample> sampleCollection = Collections.singletonList(sample);
                samples.add(new MetricFamilySamples(name, Type.GAUGE, name, sampleCollection));
            }
        }
        return samples;
    }

    @Override
    public List<MetricFamilySamples> describe()
    {
        return new ArrayList<>();
    }
}
