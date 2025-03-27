package com.arck.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arck.model.Producto;
import com.arck.service.ProductosService;

@RestController
public class ProductosController {

    @Autowired
    private ProductosService productosService;

    @GetMapping(value="productos")
    public List<Producto> getAll() {
        return productosService.getAll();
    }

    @GetMapping(value="productos/categoria/{categoria}")
    public List<Producto> getProductosByCategoria(@PathVariable String categoria) {
        return productosService.getProductosByCategoria(categoria);
    }

    @GetMapping(value="producto")
    public Producto getProductoByCodigo(@RequestParam int code) {
        return productosService.getProductoByCodigo(code);
    }

    @PostMapping(value="producto")
    public void saveProducto(@RequestBody Producto producto) {
        productosService.saveProducto(producto);
    }

    @DeleteMapping(value="producto/{code}")
    public Producto removeProducto(@PathVariable int code) {
       return productosService.removeProducto(code);
    }

    @PutMapping(value="producto")
    public Producto updateProducto(int code, double precio) {
       return productosService.updateProducto(code, precio);
    }
}
