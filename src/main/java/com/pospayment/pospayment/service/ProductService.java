package com.pospayment.pospayment.service;

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
    public void deleteProduct(String uuid) {
        productRepo.deleteByUUID(uuid);
    }

    public void updateProduct(Product product) {
        Product productFromDb = productRepo.findById(String.valueOf(product.getId())).get();

    }

    public Product getProduct(String id) {
        return productRepo.findById(id).get();
    }

    public List<Product> getAllProducts(Integer companyID) {
        return productRepo.findByCompanyID(companyID);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepo.findByCategoryUuid(category);
    }

}
