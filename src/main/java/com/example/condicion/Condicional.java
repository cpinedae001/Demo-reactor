/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.condicion;

import com.example.model.Persona;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author christianpineda
 */
public class Condicional {

    private static final Logger log = LoggerFactory.getLogger(Condicional.class);

    public void defaultIfEmpty() {
//        Mono.empty()
        Mono.just(new Persona(1, "Carol", 26))
//        Flux.empty()
                .defaultIfEmpty(new Persona(0, "Default", 0))
                .subscribe(x -> log.info(x.toString()));
    }
    
    public void takeUtil(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .takeUntil(p -> p.getEdad() > 28)
                .subscribe(x -> log.info(x.toString()));
    }
    
    public void timeOut() throws InterruptedException{
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .delayElements(Duration.ofSeconds(1))
                .timeout(Duration.ofSeconds(2))
                .subscribe(x -> log.info(x.toString()));
        
        Thread.sleep(10000);
    }
}
