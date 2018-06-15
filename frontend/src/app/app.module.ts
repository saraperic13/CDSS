import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {NavbarComponent} from "./component/navbar/navbar/navbar.component";
import {LoginComponent} from "./component/login/login.component";
import {AccountService} from "./service/account/account.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {routing} from "./app.routing";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {CanActivateAuthGuard} from "./guard/can-activate-auth-guard";
import {CanActivateAdminGuard} from "./guard/can-activate-admin-guard";
import {TokenInterceptorService} from "./interceptor/TokenInterceptorService";


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    routing,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    AccountService,
    AccountService,
    HttpClient,
    CanActivateAuthGuard,
    CanActivateAdminGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
