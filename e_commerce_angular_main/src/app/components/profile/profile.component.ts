import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { loginInfo } from 'src/app/models/logininfo';
import { LoginComponent } from '../login/login.component';
import { BehaviorSubject, Observable, ObservedValueOf } from 'rxjs';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  usedLoginInfo: any;
  tempInfo: any;
  currentUser: any;
  statement: boolean = false;

  userUpdateForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    address: new FormControl(''),
    email: new FormControl(''),
    // password: new FormControl('')
  })


  ngOnInit(): void {
     this.tempInfo = localStorage.getItem('usedLoginInfo');
     this.usedLoginInfo = JSON.parse(this.tempInfo);
     this.authService.login(this.usedLoginInfo.email, this.usedLoginInfo.password).subscribe(
      (resp) => this.currentUser = resp,
      (err) => console.log(err),
      () => console.log(this.currentUser)
     );
  }

  updateUser(): void {
    this.authService.updateUser(this.currentUser.id, this.userUpdateForm.get('email')?.value,this.currentUser.password,
    this.userUpdateForm.get('firstName')?.value, this.userUpdateForm.get('lastName')?.value, this.userUpdateForm.get('address')?.value,
    this.currentUser.admin).subscribe(
      () => console.log("Updated User Information registered"),
      (err) => console.log(err),
      () => window.location.reload()
    )
  }

}
