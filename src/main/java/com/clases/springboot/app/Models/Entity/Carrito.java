package com.clases.springboot.app.Models.Entity;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    
    private List<DetalleCarrito> items = new ArrayList<>();

    public void addItem(DetalleCarrito nuevoItem) {
        
        boolean encontrado = false;

        for (DetalleCarrito item : items) {
            // Verifica si el producto ya existe en el carrito
            if (item.getProductoId().equals(nuevoItem.getProductoId())) {
                // Actualiza la cantidad del producto existente
                item.setCantidad(item.getCantidad() + nuevoItem.getCantidad());
                encontrado = true;
                break;
            }
        }
        
        // Si el producto no está en el carrito, añádelo
        if (!encontrado) {
            items.add(nuevoItem);
        }
    }

    public void removeItem(Long productoId) {
        items.removeIf(item -> item.getProductoId().equals(productoId));
    }

     
    public List<DetalleCarrito> getItems() {
        return items;
    }

    public void setItems(List<DetalleCarrito> items) {
        this.items = items;
    }

    
}
