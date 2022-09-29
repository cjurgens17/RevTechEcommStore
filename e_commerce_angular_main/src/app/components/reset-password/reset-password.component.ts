import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  resetPasswordForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  })
  

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }
  
  changePass(): void {
    this.authService.resetPass(this.resetPasswordForm.get('email')?.value, this.resetPasswordForm.get('password')?.value).subscribe(
      () => console.log("Password changed successfully"),
      // {
      //   this.authService.loggedIn=true;
      // },
      (err) => console.log(err),
      () => this.router.navigate(['login'])
    );

}
}

  