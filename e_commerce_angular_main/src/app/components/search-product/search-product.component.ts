import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/models/product';
import { debounceTime, distinctUntilChanged, switchMap} from 'rxjs/operators';
import { Observable, Subject} from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'app-search-product',
  templateUrl: './search-product.component.html',
  styleUrls: ['./search-product.component.css']
})
export class SearchProductComponent implements OnInit {

  
  term: string = '';

  @Output()
  termTextChanged: EventEmitter<string> = new EventEmitter<string>();

  termSearchTextChanged(){
    this.termTextChanged.emit(this.term);
  }


  constructor(private productService: ProductService, private http: HttpClient) { }

  ngOnInit(): void {

    

   

    




  }


  //   this.products$ = this.searchTerms.pipe(
    
  //   //   (resp) => this.allProducts = resp,
  //   //   (err) => console.log(err),
  //   //   () => console.log("Products Retrieved")
  //   // );




  //   // this.productService.getProductByName(this.searchProductForm.get('searchBox')?.value).subscribe(
  //   //   () => console.log("Search Complete"),
  //   //   (err) => console.log(err)
  //   // );

    

  //     //wait 300 ms after each keystroke before considering the term
  //     debounceTime(200),

  //     //ignore a new term if if its the same as the previous
  //     distinctUntilChanged(),

  //     // switch to the new search Observale each time the term changes
  //     switchMap((term: string)=> this.productService.searchProduct(term))
  //   )


  // }

  // // this method is pushing a search term into the observable stream (Subject)
  // //the next() method comes from 'Subject'
  // search(term: string): void {
  //   this.searchTerms.next(term)
  // }


}
