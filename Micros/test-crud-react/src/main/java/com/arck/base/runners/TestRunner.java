package com.arck.base.runners;


import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.arck.base.model.Producto;

import reactor.core.publisher.Flux;

@Component
public class TestRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		WebClient cliente = WebClient.create("http://localhost:8002");
		Flux<Producto> flux = cliente.get().uri("/productos").accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(Producto.class);
		flux.subscribe(p->System.out.println(p.toString()));
	}

}
