import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { Subscription } from 'rxjs';
import { ProductService } from 'src/app/services/product.service';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
   cartCount!: number;
   products: {
     product: Product,
     quantity: number
   } [] = [];
  subscription!: Subscription;
  totalPrice!: number;
  cartProducts: Product[] = [];
  // subscription: any;
  allProducts: Product[] = [];


  
  


  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit(): void {
    this.productService.getCart().subscribe(
      (cart) => {
        this.products = cart.products;
        this.products.forEach(
          (element) => this.cartProducts.push(element.product)
          
        );
        this.totalPrice = cart.totalPrice;
      }      
    );

  }

  emptyCart(): void {
    let cart = {
      cartCount: 0,
      products: [],
      totalPrice: 0.00
    };
    this.productService.setCart(cart);
    this.router.navigate(['/home']);
  }


  removeItem(index: number): void {     
    let newProduct = this.products[index]
    console.log(this.totalPrice)
    console.log(newProduct.product.id, newProduct.product.price, newProduct.quantity)
    let cart = {
      cartCount: this.products.length-1,
      products: this.products,
      totalPrice: this.totalPrice - (newProduct.product.price * newProduct.quantity)
    }
    this.productService.setCart(cart); 
    this.products.splice(index, 1); 

  }

  


  }  







    

   

  



