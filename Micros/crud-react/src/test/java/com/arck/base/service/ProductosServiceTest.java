package com.arck.base.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arck.base.model.Producto;

import reactor.test.StepVerifier;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductosServiceTest {

	@Autowired
	private ProductosService productosService;

	@Test
	@Order(1)
	public void getAllTest() {
		StepVerifier.create(productosService.getAll())
		.expectNextMatches(p->p.getNombre().equals("Cocacola"))
		.expectNextMatches(p->p.getNombre().equals("Leche"))
		.expectNextMatches(p->p.getNombre().equals("Jabón"))
		.expectNextCount(5).verifyComplete();
	}
	
	@Test
	@Order(2)
	public void saveProductoTest() {
		 Producto prod = new Producto(444, "Palmerita", "Alimentación", 0.67, 60);
		 StepVerifier.create(productosService.saveProducto(prod)).expectComplete().verify();
	}

	@Test
	@Order(3)
	public void removeProductoTest() {
		StepVerifier.create(productosService.removeProducto(107))
		.expectNextMatches(p->p.getNombre().equals("Detergente")).verifyComplete();
	}
	
}
