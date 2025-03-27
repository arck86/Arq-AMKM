package com.arck.webflux.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.test.StepVerifier;

@SpringBootTest
public class NamesControllerTest {

	@Autowired
	private NamesController namesController;
	
	@Test
	void getNamesTest() {
		StepVerifier.create(namesController.getNames()).expectNext("uno").expectNext("dos","tres").expectNextCount(1).verifyComplete();
	}
}
