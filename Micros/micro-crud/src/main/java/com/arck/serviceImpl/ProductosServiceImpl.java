package com.arck.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arck.model.Producto;
import com.arck.service.ProductosService;

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
    public List<Producto> getAll() {
        return productos;
    }

    @Override
    public List<Producto> getProductosByCategoria(String categoria) {
        return productos.stream().filter(prod -> prod.getCategoria().equals(categoria)).toList();
    }

    @Override
    public Producto getProductoByCodigo(int code) {
        return productos.stream().filter(prod -> prod.getCode() == code).findFirst().orElse(null);
    }

    @Override
    public void saveProducto(Producto producto) {
        Producto prod = getProductoByCodigo(producto.getCode());
        if(prod == null){
            productos.add(producto);
        }
    }

    @Override
    public Producto removeProducto(int code) {
        Producto producto = getProductoByCodigo(code);
        if(producto != null){
            productos.removeIf(prod -> prod.getCode() == code);
        }
        return producto;
    }

    @Override
    public Producto updateProducto(int code, double precio) {
        Producto producto = getProductoByCodigo(code);
        if(producto != null){
            producto.setPrecio(precio);
        }
        return producto;
    }
}
