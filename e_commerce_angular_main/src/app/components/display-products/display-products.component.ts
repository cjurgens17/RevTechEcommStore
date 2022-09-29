import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';
import { ProductDetailsPopUpComponent } from '../product-details-pop-up/product-details-pop-up.component';

@Component({
  selector: 'app-display-products',
  templateUrl: './display-products.component.html',
  styleUrls: ['./display-products.component.css']
})
export class DisplayProductsComponent implements OnInit {

  allProducts: Product[] = [];
  productInfo!: Product;
  

  constructor(private productService: ProductService, public dialog: MatDialog) { }

  openDialog(): void {
    this.dialog.open(ProductDetailsPopUpComponent, {width: '600px', height: '600px', data: {product: this.productInfo.more_details}});
  }
  
 searchText: string = '';

 onSearchTextEntered(searchValue: string){
    this.searchText = searchValue;
    console.log(this.searchText);
 }

  ngOnInit(): void {
    this.productService.getProducts().subscribe(
      (resp) => this.allProducts = resp,
      (err) => console.log(err),
      () => console.log("Products Retrieved")
    );
  }

}
