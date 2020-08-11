/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.operador.cobinaciones;

import com.example.model.Persona;
import com.example.model.Venta;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 *
 * @author christianpineda
 */
public class Combinaciones {

    private static final Logger log = LoggerFactory.getLogger(Combinaciones.class);

    /**
     * Es util para unir varios flujos de datos, independiente del tipo de dato.
     */
    public void marge() {
        List<Persona> personas1 = new ArrayList<>();
        personas1.add(new Persona(1, "Cristhian", 29));
        personas1.add(new Persona(2, "Carol", 26));
        personas1.add(new Persona(3, "Daniela", 2));
        
        List<Persona> personas2 = new ArrayList<>();
        personas2.add(new Persona(1, "Cristhian", 29));
        personas2.add(new Persona(2, "Carol", 26));
        personas2.add(new Persona(3, "Daniela", 2));
        
        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));
        
        Flux<Persona> fx1 = Flux.fromIterable(personas1);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);
        
        Flux.merge(fx1, fx2, fx3)
                .subscribe(p -> log.info(p.toString()));
    }
    
    public void zip(){
        List<Persona> personas1 = new ArrayList<>();
        personas1.add(new Persona(1, "Cristhian", 29));
        personas1.add(new Persona(2, "Carol", 26));
        personas1.add(new Persona(3, "Daniela", 2));
        
        List<Persona> personas2 = new ArrayList<>();
        personas2.add(new Persona(1, "Cristhian", 29));
        personas2.add(new Persona(2, "Carol", 26));
        personas2.add(new Persona(3, "Daniela", 2));
        
        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));
        
        Flux<Persona> fx1 = Flux.fromIterable(personas1);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);
        
//        Flux.zip(fx1, fx2, (p1, p2)-> String.format("Flux1: %s, Flux2: %s", p1, p2 ))
//                .subscribe(x-> log.info(x));
        
        Flux.zip(fx1, fx2, fx3)
                .subscribe(x-> log.info(x.toString()));
    }
    
    public void zipWith(){
        List<Persona> personas1 = new ArrayList<>();
        personas1.add(new Persona(1, "Cristhian", 29));
        personas1.add(new Persona(2, "Carol", 26));
        personas1.add(new Persona(3, "Daniela", 2));
        
        List<Persona> personas2 = new ArrayList<>();
        personas2.add(new Persona(1, "Cristhian", 29));
        personas2.add(new Persona(2, "Carol", 26));
        personas2.add(new Persona(3, "Daniela", 2));
        
        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));
        
        Flux<Persona> fx1 = Flux.fromIterable(personas1);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);
        
        fx1.zipWith(fx2, (p1, p2) -> String.format("Flux1: %s, Flux2: %s", p1, p2))
                .subscribe(x-> log.info(x));
    }
}
