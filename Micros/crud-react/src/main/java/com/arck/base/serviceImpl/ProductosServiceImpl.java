package com.arck.base.serviceImpl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arck.base.model.Producto;
import com.arck.base.service.ProductosService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductosServiceImpl implements ProductosService {

	 private static List<Producto> productos = new ArrayList<>(List.of(
		        new Producto(100, "Cocacola", "Alimentación", 1.60, 60),
		        new Producto(101, "Leche", "Alimentación", 1.50, 50),
		        new Producto(102, "Jabón", "Limpieza", 0.93, 93),
		        new Producto(103, "Mesa", "Hogar", 125, 19),
		        new Producto(104, "Televisión", "Hogar", 650, 17),
		        new Producto(105, "Huevos", "Alimentación", 1.80, 30),
		        new Producto(106, "Fregona", "Limpieza", 6, 13),
		        new Producto(107, "Detergente", "Limpieza", 9.27, 11)
		    ));
	 
	@Override
	public Flux<Producto> getAll() {
		return Flux.fromIterable(productos);
	}

	@Override
	public Flux<Producto> getProductosByCategoria(String categoria) {
		
		return Flux.fromIterable(productos).filter(prod -> prod.getCategoria().equals(categoria));
	}

	@Override
	public Mono<Producto> getProductoByCodigo(int code) {
		return Flux.fromIterable(productos).filter(p->p.getCode()==code).next().switchIfEmpty(Mono.just(new Producto()));
	}

	@Override
	public Mono<Void> saveProducto(Producto producto) {
		return getProductoByCodigo(producto.getCode()).switchIfEmpty(Mono.just(producto).map(p->{
			productos.add(p);
			return p;
		})).then();
	}

	@Override
	public Mono<Producto> removeProducto(int code) {
		return getProductoByCodigo(code).map(p->{productos.removeIf(r->r.getCode()==code);
				return p;
			});
	}

	@Override
	public Mono<Producto> updateProducto(int code, double precio) {
		return getProductoByCodigo(code).map(p->{
				p.setPrecio(precio);
				return p;
			});
	}

}
