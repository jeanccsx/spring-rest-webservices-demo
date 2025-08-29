package com.example.ws.service;

import com.example.ws.domain.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProducService {
    private final List<Product> products;

    public ProducService(List<Product> products) {
        this.products = products;
    }

    public List<Product> search(String keyword) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase())
                        || p.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Product> loadAll() {
        return products;
    }

    public Product insert(Product product) {
        products.add(product);
        return product;
    }

    public Product update(long id, Product product) {
        Optional<Product> existingProduct = products.stream()
                .filter(p -> p.getId() == id).findFirst();

        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();
            productToUpdate.setName(product.getName());
            productToUpdate.setDescription(product.getDescription());
            productToUpdate.setPrice(product.getPrice());
            return productToUpdate;
        }
        return null; // o lanzar excepción si lo prefieres
    }
    public boolean delete(long id){
        return products.removeIf( p -> p.getId().equals(id));
    }
}