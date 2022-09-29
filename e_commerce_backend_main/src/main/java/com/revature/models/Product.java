package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String more_details;

    public Product (int quantity, double price, String description, String image, String name, String more_details){
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image = image;
        this.name = name;
        this.more_details = more_details;

    }


//    Default stock image - columnDefinition = "DEFAULT 'https://media.istockphoto.com/photos/carboard-box-wclippping-path-picture-id157318026?k=20&m=157318026&s=612x612&w=0&h=mTVCRfqtS2zKtbfKu_duQy5mvegvJqs8Z7mgQlZ06ck='"
}
