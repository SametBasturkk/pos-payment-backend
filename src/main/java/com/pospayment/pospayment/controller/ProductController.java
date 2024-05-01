package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.dto.ProductDTO;
import com.pospayment.pospayment.model.Category;
import com.pospayment.pospayment.model.Product;
import com.pospayment.pospayment.service.ProductService;
import com.pospayment.pospayment.service.StorageService;
import com.pospayment.pospayment.service.UserService;
import com.pospayment.pospayment.util.Converter;
import com.pospayment.pospayment.util.JwtToken;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    private UserService userService;

    private JwtToken jwtToken;

    private StorageService storageService;

    private Converter converter;

    public ProductController(ProductService productService, UserService userService, JwtToken jwtToken, StorageService storageService, Converter converter) {
        this.productService = productService;
        this.userService = userService;
        this.jwtToken = jwtToken;
        this.storageService = storageService;
        this.converter = converter;
    }


    @PostMapping("/create")
    public void createProduct(@RequestHeader String Authorization, @RequestBody Product product) {
        String username = jwtToken.getUsername(Authorization);
        product.setCompany(userService.getCompany(username));
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

    @GetMapping("/get-all")
    public List<ProductDTO> getAllProducts(@RequestHeader String Authorization) {

        String username = jwtToken.getUsername(Authorization);
        List<Product> products = productService.getAllProducts(userService.getCompany(username));
        List<ProductDTO> productDTO = new ArrayList<>();
        for (Product p : products) {
            productDTO.add(converter.convertToDTO(p, ProductDTO.class));
        }

        return productDTO;

    }

    @PostMapping("/get-by-category")
    public List<Product> getProductsByCategory(@RequestParam Category category) {
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
