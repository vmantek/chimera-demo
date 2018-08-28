package com.vmantek.chimera.demo;

import com.vmantek.chimera.JPosApplication;
import com.vmantek.chimera.q2.EnableQ2;
import com.vmantek.chimera.q2.Q2Mods;
import com.vmantek.chimera.q2.SpringHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;

@SpringBootApplication
@EnableQ2
public class Main
{
    public static void main(String[] args)
    {
        JPosApplication.run(Main.class,args);
    }
}
