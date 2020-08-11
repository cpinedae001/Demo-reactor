/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.matematicos;

import com.example.model.Persona;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 *
 * @author christianpineda
 */
public class Matematico {

    private static final Logger log = LoggerFactory.getLogger(Matematico.class);
    
    public void average(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .collect(Collectors.averagingInt(Persona::getEdad))
                .subscribe(x -> log.info(x.toString()));
    }
    
    public void count(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .count()
                .subscribe(x -> log.info("Cantidad de personas: " + x.toString()));
        
    }
    
    public void min(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .collect(Collectors.minBy(Comparator.comparing(Persona::getEdad)))
                .subscribe(x -> log.info(x.toString()));
    }
    
    public void sum(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .collect(Collectors.summarizingInt(Persona::getEdad))
                .subscribe(x -> log.info(x.toString()));
        
        Flux.fromIterable(pesonas)
                .collect(Collectors.summingInt(Persona::getEdad))
                .subscribe(x -> log.info(x.toString()));
    }

}
