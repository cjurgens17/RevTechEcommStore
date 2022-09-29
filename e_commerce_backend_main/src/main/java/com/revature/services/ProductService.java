package com.revature.services;

import com.revature.dtos.ProductInfo;
import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    //Autowire maybe?
    private final ProductRepository productRepository;

    //Autowire maybe?
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Get All Products
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    //Creating a New Product
    public Product save(Product product) {
        return productRepository.save(product);
    }

    //ALL AGREE NOT TO USE THIS METHOD
    public List<Product> saveAll(List<Product> productList, List<ProductInfo> metadata) {
    	return productRepository.saveAll(productList);
    }

    //This method updates the product
    public int updateProduct(Product product){
        return productRepository.updateProduct(product.getDescription(), product.getImage(), product.getMore_details(),
                product.getName(), product.getPrice(), product.getQuantity(), product.getId());
    }

    //This method gets a product by name
    public List<Product> getProductByName(String name){
        return productRepository.getProductByName(name);
    }

    //Delete Product by Id
    public void delete(int id) {
        productRepository.deleteById(id);
    }
}
