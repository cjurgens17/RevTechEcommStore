import { Component, Input, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';
import { ProductDetailsPopUpComponent } from 'src/app/components/product-details-pop-up/product-details-pop-up.component';
import { MatDialog} from '@angular/material/dialog'

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit{

  cartCount!: number;
  products: {
    product: Product,
    quantity: number   
  }[]=[];
  subscription!: Subscription;
  totalPrice: number = 0;

  @Input() productInfo!: Product;
  @Input() itemqty!:number;
  itemQuantity:number[]=[];
  initialvalue:boolean=true;

   constructor(private productService: ProductService, public dialog: MatDialog) { }
  
  ngOnInit(): void {
    this.subscription = this.productService.getCart().subscribe(
      (cart) => {
        this.cartCount = cart.cartCount;
        this.products = cart.products;
        this.totalPrice = cart.totalPrice;   
        this.products.forEach(
          (element) => {   
             if(element.product.id == this.productInfo.id) {              
              this.itemQuantity[element.product.id]=element.quantity;   
              this.itemqty =this.itemQuantity[element.product.id]; 
             }
          });    
       
      }                        
      );
   
    }

  openDialog(): void {
    this.dialog.open(ProductDetailsPopUpComponent, {width: '600px', height: '600px', data: {product: this.productInfo.more_details}});
  }
  

  addToCart(product: Product): void {
    let inCart = false;    
    // this.products.forEach(    
    //   (element) => {
    //     if(element.product == product){   
    //       this.itemQuantity[element.product.id]=this.itemqty;
    //       let cart = {
    //         cartCount: this.cartCount,
    //         products: this.products,
    //         totalPrice: this.totalPrice + product.price
    //       };          
    //       this.productService.setCart(cart);
    //       inCart=true;
    //       return;
    //     };
    //   }
    // );

    if(inCart == false){  
      console.log(this.itemqty)
      let newProduct = {
         product: product,
         quantity: this.itemqty
      };
      this.products.push(newProduct);
      let cart = {
        cartCount: this.cartCount + this.itemqty,
        products: this.products,
        totalPrice: this.totalPrice + (product.price * this.itemqty)
      }
      this.itemQuantity[newProduct.product.id]=this.itemqty;
      this.productService.setCart(cart);
    }
      
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
