import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import {map} from 'rxjs/operators';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  //DECLARATIONS FOR SAVING USER
  // private userSubject:BehaviorSubject<User>;
  // public user: Observable<User>;

  authUrl: string = `${environment.baseUrl}/auth`;
  loggedIn: boolean = false;

  private currentUser = new BehaviorSubject<User>({
    id: 0,
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    address: '',
    admin: false
  });

  private currentUser$ = this.currentUser.asObservable();

  //ADDED PRIVATE ROUTER TO CONSTRUCTOR
  constructor(private http: HttpClient, private router: Router) {}
   
  getCurrentUser(): Observable<User> {
    return this.currentUser$;
  }

  setCurrentUser(latestValue: User) {
    return this.currentUser.next(latestValue);
  }

  //this is the original code
  login(email: string, password: string): Observable<any> {
    const payload = {email:email, password:password};

    console.log("this works");
    return this.http.post<any>(`${this.authUrl}/login`, payload, {headers: environment.headers, withCredentials: environment.withCredentials})
  }

  logout(): void{
    this.http.post(`${this.authUrl}/logout`, null);
  }

  register(firstName: string, lastName: string, email: string, password: string, address: string): Observable<any> {
    const payload = {firstName: firstName, lastName: lastName, email: email, password: password, address: address};
    return this.http.post<any>(`${this.authUrl}/register`, payload, {headers: environment.headers});
  }

  // This is where we are mapping the password reset api to 

  resetPass(email:string, password: string){
    const payload = {email:email, password:password};
    console.log(payload)
    return this.http.put(`${this.authUrl}/resetPassword`, payload, {headers: environment.headers});
    

  }

  updateUser(id: number, email: string, password: string, firstName: string, lastName: string, address: string, admin: boolean): Observable<any> {
    const payload = {id: id, email: email, password: password, firstName: firstName, lastName: lastName, address: address, admin: admin};
    console.log(payload)
    return this.http.put(`${this.authUrl}/updateUser`, payload, {headers: environment.headers});
  }


}
