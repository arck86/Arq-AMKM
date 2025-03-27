package com.arck.base.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arck.base.model.Producto;
import com.arck.base.service.ProductosService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin("*")
public class ProductorController {
	
	@Autowired
	private ProductosService productosService;
	
    @GetMapping(value="productos")
	public ResponseEntity<Flux<Producto>> getAll() {
		return new ResponseEntity<>(productosService.getAll(), HttpStatus.OK);
	}

    @GetMapping(value="productos/categoria/{categoria}")
	public ResponseEntity<Flux<Producto>> getProductosByCategoria(@PathVariable String categoria) {
		return new ResponseEntity<>(productosService.getProductosByCategoria(categoria), HttpStatus.OK);
	}
    
    @GetMapping(value="producto")
	public ResponseEntity<Mono<Producto>> getProductoByCodigo(int code) {
		return new ResponseEntity<>(productosService.getProductoByCodigo(code), HttpStatus.OK);
	}

    @PostMapping(value="producto")
	public ResponseEntity<Mono<Void>> saveProducto(Producto producto) {
		return new ResponseEntity<>(productosService.saveProducto(producto), HttpStatus.OK);
	}

    @DeleteMapping(value="producto/{code}")
	public Mono<ResponseEntity<Producto>> removeProducto(@PathVariable int code) {
    	return productosService.removeProducto(code).map(p-> new ResponseEntity<>(p,HttpStatus.OK)).switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
	}

    @PutMapping(value="producto")
	public Mono<ResponseEntity<Producto>> updateProducto(int code, double precio) {
    	return productosService.updateProducto(code, precio).map(p-> new ResponseEntity<>(p,HttpStatus.OK)).switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

}
