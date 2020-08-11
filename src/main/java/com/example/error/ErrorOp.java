/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.error;

import com.example.model.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.management.RuntimeErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author christianpineda
 */
public class ErrorOp {

    private static final Logger log = LoggerFactory.getLogger(ErrorOp.class);

    public void retry() {

        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .concatWith(Flux.error(new RuntimeException("un error")))
                .retry(1)
                .doOnNext(x -> log.info(x.toString()))
                .subscribe();
    }
    
    
    public void errorReturn(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .concatWith(Flux.error(new RuntimeException("un error")))
                .onErrorReturn(new Persona(0, "xyz", 99))
                .subscribe(x -> log.info(x.toString()));
    }
    
    public void errorREsume(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .concatWith(Flux.error(new RuntimeException("un error")))
                .onErrorResume(e -> Mono.just(new Persona(0, "xy", 100)))
                .subscribe(x -> log.info(x.toString()));
    }
    
    public void errorMap(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas)
                .concatWith(Flux.error(new RuntimeException("un error")))
                .onErrorMap(e -> new InterruptedException("error en el map"))
                .subscribe(x -> log.info(x.toString()));
    }
}
