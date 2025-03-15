package com.example.seversteel.service.impl;

import com.example.seversteel.models.Product;
import com.example.seversteel.repository.ProductRepository;
import com.example.seversteel.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
