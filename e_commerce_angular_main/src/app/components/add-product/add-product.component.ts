import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ProductService } from 'src/app/services/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  //Setting up FormGroup to pull values from HTML page
  newProductForm = new FormGroup({
    quantity: new FormControl(''),
    price: new FormControl(''),
    description: new FormControl(''),
    image: new FormControl(''),
    name: new FormControl(''),
    more_details: new FormControl('')
  })


  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit(): void {
  }

  //Method to sent post request to backend to enter new product into our database
  onProductSubmit(): void {
    this.productService.addProduct(this.newProductForm.get('quantity')?.value, this.newProductForm.get('price')?.value,
     this.newProductForm.get('description')?.value, this.newProductForm.get('image')?.value,
      this.newProductForm.get('name')?.value, this.newProductForm.get('more_details')?.value).subscribe(
      () => console.log("New Product registered"),
      (err) => console.log(err),
      () => this.router.navigate(['home'])
     );
  }

}
