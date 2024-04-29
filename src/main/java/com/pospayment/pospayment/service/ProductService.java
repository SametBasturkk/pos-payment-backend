package com.pospayment.pospayment.service;

import com.pospayment.pospayment.model.Category;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.Product;
import com.pospayment.pospayment.repository.ProductRepo;
import jakarta.transaction.Transactional;
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

    @Transactional
    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }

    public void updateProduct(Product product) {
        Product productFromDb = productRepo.findById(String.valueOf(product.getId())).get();

    }

    public Product getProduct(String id) {
        return productRepo.findById(id).get();
    }

    public List<Product> getAllProducts(Company company) {
        return productRepo.findByCompany(company);
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepo.findByCategory(category);
    }

}
