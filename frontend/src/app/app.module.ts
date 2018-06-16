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
import { HomeComponent } from './component/home/home.component';
import { MedicalChartListComponent } from './component/medical_chart/medical-chart-list/medical-chart-list.component';
import { MedicalChartFormComponent } from './component/medical_chart/medical-chart-form/medical-chart-form.component';
import { MedicalChartPageComponent } from './component/medical_chart/medical-chart-page/medical-chart-page.component';
import {MedicalChartService} from "./service/medical-chart/medical-chart.service";
import {CanActivateDoctorGuard} from "./guard/can-activate-doctor-guard";


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    HomeComponent,
    MedicalChartListComponent,
    MedicalChartFormComponent,
    MedicalChartPageComponent
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
    MedicalChartService,
    HttpClient,
    CanActivateAuthGuard,
    CanActivateAdminGuard,
    CanActivateDoctorGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
