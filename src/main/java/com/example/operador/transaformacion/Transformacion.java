/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.operador.transaformacion;

import com.example.model.Persona;
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
public class Transformacion {

    private static final Logger log = LoggerFactory.getLogger(Transformacion.class);

    public void map() {
//        List<Persona> pesonas = new ArrayList<>();
//        pesonas.add(new Persona(1, "Cristhian", 29));
//        pesonas.add(new Persona(2, "Carol", 26));
//        pesonas.add(new Persona(3, "Daniela", 2));
//
//        Flux.fromIterable(pesonas)
//                .map(p -> { 
//                   p.setEdad(p.getEdad() +10);
//                   p.setId(p.getId() + 3);
//                   return p;
//                })
//                .subscribe(p -> log.info(p.toString()));
        Flux<Integer> fx = Flux.range(0, 10);
        Flux<Integer> fx2 = fx.map(p -> p + 10);
        fx2.subscribe(x -> log.info("x: " + x));

    }

    /***
     * Es similar al meétodo map con una pequeña 
     * singularidad. 
     * 
     * Se puede utilizar para hacer llamdas a base de datos y retornar un mono del objeto 
     * que se retorno y luego ejecutar una sentancia de eliminacion.
     */
    public void flatMap() {
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));
        Flux.fromIterable(pesonas)
                .flatMap(p -> {
                    p.setEdad(p.getEdad() + 10);
                    return Mono.just(p);
                })
                .subscribe(p -> log.info(p.toString()) );
    }
    
    public void groupBy(){
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(1, "Carol", 26));
        pesonas.add(new Persona(2, "Daniela", 2));
        pesonas.add(new Persona(2, "Daniela", 3));
        Flux.fromIterable(pesonas)
                .groupBy(Persona::getId)
                .flatMap(idFlux -> idFlux.collectList())
                .subscribe(x -> log.info(x.toString()));
    }

}
