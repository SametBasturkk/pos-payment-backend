package com.pospayment.pospayment.service;

import com.pospayment.pospayment.dto.ProductDTO;
import com.pospayment.pospayment.model.Category;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.Product;
import com.pospayment.pospayment.repository.ProductRepo;
import com.pospayment.pospayment.util.Converter;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private ProductRepo productRepo;

    private Converter converter;

    public ProductService(ProductRepo productRepo, Converter converter) {
        this.productRepo = productRepo;
        this.converter = converter;
    }

    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    @Transactional
    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }

    public void updateProduct(Product product) {
        productRepo.save(product);
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

    public List<ProductDTO> getProductsByCategoryDTO(Category category) {
        List<Product> products = productRepo.findByCategory(category);
        List<ProductDTO> productDTO = new ArrayList<>();
        for (Product product : products) {
            productDTO.add(converter.convertToDTO(product, ProductDTO.class));
        }

        return productDTO;
    }

}
