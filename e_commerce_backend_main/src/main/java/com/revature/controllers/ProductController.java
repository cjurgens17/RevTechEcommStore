package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.ProductInfo;
import com.revature.models.Product;
import com.revature.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(allowedHeaders = "*", origins = {"http://localhost:4200", "http://localhost:3000", "http://54.145.202.78:8080"}, allowCredentials = "true")
public class ProductController {


    //Autowire maybe?
    private final ProductService productService;

    //Autowire maybe?
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @Authorized
    @GetMapping
    public ResponseEntity<List<Product>> getInventory() {
        return ResponseEntity.ok(productService.findAll());
    }

    @Authorized
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        Optional<Product> optional = productService.findById(id);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    //This is our fallback for the search bar, we should be able to get a product by name
    @Authorized
    @PostMapping(value = "/getProductByName",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductByName(@RequestBody Product product){
        String name = product.getName();
        System.out.println(name);
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    //This is the new updateProduct method
    @Authorized
    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        int pk = productService.updateProduct(product);

        if(pk > 0){
            return ResponseEntity.ok(product);
        }
        return null;
    }



    //NEW IMPROMTU ADD PRODUCT METHOD
    @Authorized
    @PutMapping(value="/addNewProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> upsert(@RequestBody Product product) {
        System.out.println(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }


    //LETS JUST NOT USE THIS
    @Authorized
    @PatchMapping
    public ResponseEntity<List<Product>> purchase(@RequestBody List<ProductInfo> metadata) { 	
    	List<Product> productList = new ArrayList<Product>();
    	
    	for (int i = 0; i < metadata.size(); i++) {
    		Optional<Product> optional = productService.findById(metadata.get(i).getId());

    		if(!optional.isPresent()) {
    			return ResponseEntity.notFound().build();
    		}

    		Product product = optional.get();

    		if(product.getQuantity() - metadata.get(i).getQuantity() < 0) {
    			return ResponseEntity.badRequest().build();
    		}
    		
    		product.setQuantity(product.getQuantity() - metadata.get(i).getQuantity());
    		productList.add(product);
    	}
        
        productService.saveAll(productList, metadata);

        return ResponseEntity.ok(productList);
    }

    @Authorized
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) {
        Optional<Product> optional = productService.findById(id);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productService.delete(id);

        return ResponseEntity.ok(optional.get());
    }
}
