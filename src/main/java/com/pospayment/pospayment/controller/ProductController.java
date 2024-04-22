package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.model.Product;
import com.pospayment.pospayment.service.ProductService;
import com.pospayment.pospayment.service.StorageService;
import com.pospayment.pospayment.service.UserService;
import com.pospayment.pospayment.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private StorageService storageService;


    @PostMapping("/create")
    public void createProduct(@RequestHeader String Authorization, @RequestBody Product product) {
        String username = jwtToken.getUsername(Authorization);
        product.setCompanyID(userService.getCompanyID(username));
        productService.saveProduct(product);
    }

    @PostMapping("/delete")
    public void deleteProduct(@RequestParam String uuid) {
        productService.deleteProduct(uuid);
    }

    @PostMapping("/get")
    public Product getProduct(@RequestParam String id) {
        return productService.getProduct(id);
    }

    @GetMapping("/get-all")
    public List<Product> getAllProducts(@RequestHeader String Authorization) {
        String username = jwtToken.getUsername(Authorization);
        return productService.getAllProducts(userService.getCompanyID(username));
    }

    @PostMapping("/get-by-category")
    public List<Product> getProductsByCategory(@RequestParam String category) {
        return productService.getProductsByCategory(category);
    }

    @PostMapping("/image-upload")
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        return storageService.store(file);
    }

    @GetMapping("/image/{filename:.+}")
    public Resource getImage(@PathVariable String filename) {
        return storageService.loadFile(filename);
    }


}
