import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';



@Component({
  selector: 'app-product-details-pop-up',
  templateUrl: './product-details-pop-up.component.html',
  styleUrls: ['./product-details-pop-up.component.css']
})
export class ProductDetailsPopUpComponent implements OnInit {

  



  constructor(public dialogRef: MatDialogRef<ProductDetailsPopUpComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }



 
  

  

  ngOnInit(): void {

    
    
   
    
  

   

  }

}
