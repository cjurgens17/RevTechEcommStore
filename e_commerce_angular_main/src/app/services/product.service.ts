import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { Product } from '../models/product';
import { environment } from 'src/environments/environment';
import { newProduct} from '../models/new-product';
import { catchError, tap } from 'rxjs/operators';
import { MessageService } from './message.service';


interface Cart {
  cartCount: number;
  products: {
    product: Product,
    quantity: number
  }[];
  totalPrice: number;
}

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productUrl: string = "/api/product";
  // private addProductUrl: string = "/api/product/addNewProduct";
  // baseUrl: "http://54.145.202.78:8080",

  private _cart = new BehaviorSubject<Cart>({
    cartCount: 0,
    products: [],
    totalPrice: 0.00
  });

  private _cart$ = this._cart.asObservable();

  getCart(): Observable<Cart> {
    return this._cart$;
  }

  setCart(latestValue: Cart) {
    return this._cart.next(latestValue);
  }

  constructor(private http: HttpClient, private messageService: MessageService) { }

  public getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(environment.baseUrl+this.productUrl, {headers: environment.headers, withCredentials: environment.withCredentials});
  }

  public getSingleProduct(id: number): Observable<Product> {
    return this.http.get<Product>(environment.baseUrl+id);
  }

  /*public getProductByName(name: string): Observable<Product> {
    return this.http.get<Product>(environment.baseUrl+name);
  }*/
  searchProduct(term: string): Observable<Product[]> {

    //  if the search term is empty we will return an empty array
    if(!term.trim()) {
      return of([]);
    }
    console.log("hello")
  
    return this.http.get<Product[]>(environment.baseUrl+this.productUrl).pipe(
      tap (x => x.length ? this.log(`found products matching '${term}'`) : this.log(` no prodcut matching '${term}'`)),
      catchError(this.handleError<Product[]>('searchProduct', []))
  
      )
  }


  public purchase(products: {id:number, quantity:number}[]): Observable<any> {
    const payload = JSON.stringify(products);
    return this.http.patch<any>(environment.baseUrl+this.productUrl, payload, {headers: environment.headers, withCredentials: environment.withCredentials})
  }

  public addProduct(quantity: number, price: number, description: string, image: string, name: string, more_details: string): Observable<any> {
    const payload = {quantity: quantity, price: price, description: description, image: image, name: name, more_details: more_details};
    console.log(payload);
    return this.http.put<any>(environment.baseUrl+this.productUrl, payload, {headers: environment.headers});
  }

  private handleError<T>(operation = 'operation', result?:T){
    return(error:any): Observable<T> => {
  
      console.error(error);
      this.log(`${operation} failed: ${error.name}`)
      console.log(error)
      return of(result as T);
  
    }
  }
  
  private log(message: string) {
    this.messageService.add(`Product Service: ${message}`)
    console.log(message)
  }
   
  }



