package com.Product.product.controller;

import com.Product.product.dto.ProductDto;
import com.Product.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/getproducts")
    public List<ProductDto> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{productId}")
    public ProductDto getProductById(@PathVariable Integer productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/addproduct")
    public ProductDto saveProduct(@RequestBody ProductDto productDTO) {
        return productService.saveProduct(productDTO);
    }

    @PutMapping("/updateproduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDTO) {
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping("/deleteproduct/{productId}")
    public String deleteProduct(@PathVariable Integer productId) {
        return productService.deleteProduct(productId);
    }
}
