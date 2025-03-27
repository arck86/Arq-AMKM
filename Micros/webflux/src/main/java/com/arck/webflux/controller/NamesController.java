package com.arck.webflux.controller;

import java.time.Duration;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class NamesController {

	@GetMapping(value="names")
	public Flux<String> getNames(){
		List<String> lista = List.of("uno","dos","tres","cuatro");
		return Flux.fromIterable(lista).delayElements(Duration.ofSeconds(2));
	}
}
