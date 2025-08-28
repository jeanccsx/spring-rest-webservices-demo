package com.example.ws.service;
import com.example.ws.domain.Product;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducService
{
    private static List<Product> products = List.of();

    public ProducService(List<Product> products) {
        this.products = products;
    }
    public List<Product> search(String keyword){
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase())
                        || p.getDescription().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
    }
    public static List<Product> loadAll(){
        return products;
}
    public Product insert(Product product){
        products.add(product);
        return product;
    }
}
