package com.example.testdbconection.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public List<Product> getALlProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Optional<Product> getProductWithHighestPrice(@PathVariable Integer id){
        return productRepository.findById(id);
    }

    @PostMapping(
            value = "/newProduct", consumes = "application/json", produces = "application/json")
    public Product newProduct(@RequestBody Product product){
        return productRepository.save(product);
   }

   @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Integer id){
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return "Product with id " + id + " deleted";
        } else {
            return "Product with id " + id + " does not exist";
        }
    }



}
