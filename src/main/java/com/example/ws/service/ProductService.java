package com.example.ws.service;

import com.example.ws.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final List<Product> products;
    private final AtomicLong seq;

    public ProductService(List<Product> products) {
        this.products = products;
        long maxId = products.stream()
                .mapToLong(p -> p.getId() != null ? p.getId() : 0L)
                .max()
                .orElse(0L);
        this.seq = new AtomicLong(maxId);
    }

    public List<Product> search(String keyword) {
        String kw = keyword == null ? "" : keyword.toLowerCase();
        return products.stream()
                .filter(p -> (p.getName() != null && p.getName().toLowerCase().contains(kw)) ||
                        (p.getDescription() != null && p.getDescription().toLowerCase().contains(kw)))
                .collect(Collectors.toList());
    }

    public List<Product> loadAll() {
        return products;
    }

    public Product insert(Product product) {
        if (product.getId() == null || product.getId() <= 0) {
            product.setId(seq.incrementAndGet());
        } else {
// si trae id explícito, aseguramos el contador
            seq.updateAndGet(curr -> Math.max(curr, product.getId()));
        }
        products.add(product);
        return product;
    }

    public Product update(Long id, Product product) {
        Optional<Product> existingProduct = products.stream()
                .filter(p -> p.getId() != null && p.getId().equals(id)).findFirst();

        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();
            productToUpdate.setName(product.getName());
            productToUpdate.setDescription(product.getDescription());
            productToUpdate.setPrice(product.getPrice());
            return productToUpdate;
        }
        return null; // luego podemos devolver Optional o lanzar excepción si prefieres
    }

    public boolean delete(Long id){
        return products.removeIf( p -> p.getId() != null && p.getId().equals(id));
    }
}