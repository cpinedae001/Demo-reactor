/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.operador.creacion;

import com.example.model.Persona;
import io.reactivex.Observable;
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
public class Creacion {

    private static final Logger log = LoggerFactory.getLogger(Creacion.class);
    //operadores de creacion que genera flujos de datos mono o flux, operaciones empty, repeat

    public void justFrom() {
        Mono.just(new Persona(1, "Cristhian", 29));
//        Flux.fromIterable(coleccion);
//        Observable.just(item);
    }

    //para validar si viene vacio.
    public void empty() {
        Mono.empty();
        Flux.empty();
        Observable.empty();
    }
    //rangos de algo
    public void range(){
        Flux.range(0, 3)
                .doOnNext(i -> log.info("i: " + i))
                .subscribe();
    }
    
    //para repetir algo
    public void repeat(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));
        
        Flux.fromIterable(pesonas)
                .repeat(3)
                .subscribe(p -> log.info(p.toString()));
        
        Mono.just(new Persona(1, "Cristhian", 0))
                .repeat(3)
                .subscribe(p -> log.info(p.toString()));
    }

}
