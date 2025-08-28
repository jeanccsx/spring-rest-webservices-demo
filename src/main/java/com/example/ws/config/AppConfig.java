package com.example.ws.config;
import com.example.ws.domain.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public List<Product> products() {
        List<Product> list = new ArrayList<>();
        list.add(new Product(1L, "MacbookPro", "M3 Chip", 1999.9));
        list.add(new Product(3L, "AirpodPro", "Noise Cancellation", 299.9));
        list.add(new Product(2L, "IpadPro", "M3 Chip", 999.9));
        return list;
    }
}
