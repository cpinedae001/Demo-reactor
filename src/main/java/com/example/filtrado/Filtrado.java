/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.filtrado;

import com.example.model.Persona;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 *
 * @author christianpineda
 */
public class Filtrado {

    private static final Logger log = LoggerFactory.getLogger(Filtrado.class);

    public void filter() {
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .filter(p -> p.getEdad() > 28)
                .subscribe(p -> log.info(p.toString()));
    }
    
    public void distinct(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(1, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .distinct()
                .subscribe(p -> log.info(p.toString()));
    }
    
    public void take(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(1, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .take(2)
                .subscribe(p -> log.info(p.toString()));
        
    }

    public void takeLike(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .takeLast(2)
                .subscribe(p -> log.info(p.toString()));
        
    }
    /**
     * Evitar el primer resultado
     */
    public void skip(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .skip(1)
                .subscribe(p -> log.info(p.toString()));
        
    }
    
    /***
     * Evitar el ultimo resultado
     */
    public void skipLast(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .skipLast(1)
                .subscribe(p -> log.info(p.toString()));
        
    }
}
