package com.example;

import com.example.condicion.Condicional;
import com.example.error.ErrorOp;
import com.example.filtrado.Filtrado;
import com.example.matematicos.Matematico;
import com.example.model.Persona;
import com.example.operador.cobinaciones.Combinaciones;
import com.example.operador.creacion.Creacion;
import com.example.operador.transaformacion.Transformacion;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class BasicApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BasicApplication.class);

    public void reactor() {
        Mono.just(new Persona(1, "cristhian", 29))
                .doOnNext(p -> {
                    //logica adicional
                    log.info("Reactor, Persona: " + p);
                })
                .subscribe(p -> log.info("Reactor, persona: " + p));
    }

    public void rxJava2() {
        Observable.just(new Persona(1, "cristhian", 29))
                .doOnNext(p -> log.info("RxJava2, persona: " + p))
                .subscribe(p -> log.info("RxJava2, persona: " + p));

    }

    public void mono() {
        Mono.just(new Persona(1, "Cristhian", 0))
                .subscribe(p -> log.info(p.toString()));
    }

    //tipo dato asincrono 
    public void flux() {
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux.fromIterable(pesonas).subscribe(p -> log.info(p.toString()));

    }

    //para publicar un arreglo dirrectamente y no recorrer todo el arreglo usar lo siguiente.
    //esto da como resultado un mono.
    public void fluxMono() {
        List<Persona> pesonas = new ArrayList<>();
        pesonas.add(new Persona(1, "Cristhian", 29));
        pesonas.add(new Persona(2, "Carol", 26));
        pesonas.add(new Persona(3, "Daniela", 2));

        Flux<Persona> fx = Flux.fromIterable(pesonas);
        fx.collectList()
                .subscribe(lista -> log.info(lista.toString()));

    }

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        reactor();
//        rxJava2();
//        mono();
//        flux();
//        fluxMono();
//        Creacion app = new Creacion();
//        app.repeat();
//        Filtrado app = new Filtrado();
        Matematico app = new Matematico();
        app.sum();

    }
    
    
}
