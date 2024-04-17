package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.model.Product;
import com.pospayment.pospayment.service.ProductService;
import com.pospayment.pospayment.service.StorageService;
import com.pospayment.pospayment.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private StorageService storageService;


    @PostMapping("/create")
    public void createProduct(Product product) {
        productService.saveProduct(product);
    }

    @PostMapping("/delete")
    public void deleteProduct(@RequestParam String id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/get")
    public Product getProduct(@RequestParam String id) {
        return productService.getProduct(id);
    }

    @PostMapping("/get-all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/get-by-category")
    public List<Product> getProductsByCategory(@RequestParam String category) {
        return productService.getProductsByCategory(category);
    }

    @PostMapping("/image-upload")
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        return storageService.store(file);
    }


}
