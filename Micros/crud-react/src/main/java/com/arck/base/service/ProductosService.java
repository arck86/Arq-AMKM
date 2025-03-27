package com.arck.base.service;

import com.arck.base.model.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductosService {

	Flux<Producto> getAll();
	Flux<Producto> getProductosByCategoria(String categoria);
    Mono<Producto> getProductoByCodigo(int code);
    Mono<Void> saveProducto(Producto producto);
    Mono<Producto> removeProducto(int code);
    Mono<Producto> updateProducto(int code, double precio);
}
