import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import { profile } from 'console';
import { CartComponent } from './components/cart/cart.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { DisplayProductsComponent } from './components/display-products/display-products.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { SearchProductComponent } from './components/search-product/search-product.component';
import { ProductCardComponent } from './components/product-card/product-card.component';

const routes: Routes = [


  {path: "user",component:ProfileComponent},
  { path: "", redirectTo: "/login", pathMatch: "full" },
  { path: "login", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  { path: "home", component: DisplayProductsComponent },
  { path: "cart", component: CartComponent },
  { path: "checkout", component: CheckoutComponent },
  { path: "addProduct", component: AddProductComponent},
  { path: "reset-password", component:ResetPasswordComponent},
  { path: "search-product", component:SearchProductComponent},
  { path: "product-card", component:ProductCardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
