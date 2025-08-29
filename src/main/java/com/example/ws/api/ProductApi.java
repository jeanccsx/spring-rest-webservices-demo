package com.example.ws.api;

import com.example.ws.service.ProducService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ws.domain.Product;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductApi {

    private final ProducService producService;

    public ProductApi(ProducService producService){
        this.producService = producService;
    }

    @GetMapping
    public List<Product> findAll(){
        return producService.loadAll();
    }

    @GetMapping("/search")
    public List<Product> search (@RequestParam String keyword){
        return producService.search(keyword);
    }

    @PostMapping
    public ResponseEntity<Product> addNew(@RequestBody Product product){
        Product saved = producService.insert(product);
        return ResponseEntity.status(201).body(saved);
    }
}