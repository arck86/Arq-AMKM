package com.arck.base.runners;


import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.arck.base.model.Producto;

import reactor.core.publisher.Mono;

@Component
public class TestRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		WebClient cliente = WebClient.create("http://localhost:8002");
		
//		cliente.post().uri("/producto").body(Mono.just(new Producto(200,"Pipas", "Alimentación", 0.90, 20)), Producto.class)
//		.retrieve().bodyToMono(Void.class).doOnTerminate(()->System.out.println("Dado de alta")).block();
//		
//		Flux<Producto> flux = cliente.get().uri("/productos").accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(Producto.class);
//		flux.subscribe(p->System.out.println(p.toString()));
		
		Mono<Producto> monoDelete=cliente
				.delete()
				.uri("/producto/101")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(h->h.is4xxClientError(), t->{
					System.out.println("No se encontró el producto");
					return Mono.empty();
				})
				.bodyToMono(Producto.class);
		monoDelete.subscribe(p->System.out.println(p));
		monoDelete.block();
	}

}
