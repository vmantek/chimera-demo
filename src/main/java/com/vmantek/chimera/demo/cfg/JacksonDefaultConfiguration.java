package com.vmantek.chimera.demo.cfg;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;

@Configuration
public class JacksonDefaultConfiguration
{
    @Bean
    public Module getJacksonHibernateDatatype()
    {
        return new Hibernate5Module();
    }

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder()
    {
        return new Jackson2ObjectMapperBuilder()
            .serializationInclusion(Include.NON_NULL)
            .indentOutput(true)
            .dateFormat(new SimpleDateFormat("yyyyMMddhhmmss"));
    }
}
