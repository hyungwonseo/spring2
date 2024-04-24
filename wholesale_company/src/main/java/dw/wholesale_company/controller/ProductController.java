package dw.wholesale_company.controller;

import dw.wholesale_company.model.Product;
import dw.wholesale_company.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductAll() {
        return new ResponseEntity<>(productService.getProductAll(),
                HttpStatus.OK);
    }

    @GetMapping("/products/inventory/under/{num}")
    public ResponseEntity<List<Product>> getProductByInventoryUnder(@PathVariable int num) {
        return new ResponseEntity<>(productService.getProductByInventoryUnder(num),
                HttpStatus.OK);
    }

    @GetMapping("/products/{productName}")
    public ResponseEntity<List<Product>> getProductByProductName(@PathVariable String productName) {
        return new ResponseEntity<>(productService.getProductByProductName(productName),
                HttpStatus.OK);
    }

    @GetMapping("/products/price")
    public ResponseEntity<List<Product>> getProductByPriceRange(@RequestParam int low,
                                                                @RequestParam int high) {
        return new ResponseEntity<>(productService.getProductByPriceRange(low, high),
                HttpStatus.OK);
    }
}
