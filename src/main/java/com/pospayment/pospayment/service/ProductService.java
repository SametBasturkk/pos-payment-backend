package com.pospayment.pospayment.service;

import com.pospayment.pospayment.model.Product;
import com.pospayment.pospayment.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }

    public void updateProduct(Product product) {
        Product productFromDb = productRepo.findById(String.valueOf(product.getId())).get();

    }

    public Product getProduct(String id) {
        return productRepo.findById(id).get();
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepo.findByCategory(category);
    }

}
