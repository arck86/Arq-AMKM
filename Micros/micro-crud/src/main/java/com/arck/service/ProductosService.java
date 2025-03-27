package com.arck.service;

import java.util.List;

import com.arck.model.Producto;

public interface ProductosService {

    List<Producto> getAll();
    List<Producto> getProductosByCategoria(String categoria);
    Producto getProductoByCodigo(int code);
    void saveProducto(Producto producto);
    Producto removeProducto(int code);
    Producto updateProducto(int code, double precio);
}
