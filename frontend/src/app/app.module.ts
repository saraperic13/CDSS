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
import {HomeComponent} from './component/home/home.component';
import {MedicalChartListComponent} from './component/medical_chart/medical-chart-list/medical-chart-list.component';
import {MedicalChartFormComponent} from './component/medical_chart/medical-chart-form/medical-chart-form.component';
import {MedicalChartPageComponent} from './component/medical_chart/medical-chart-page/medical-chart-page.component';
import {MedicalChartService} from "./service/medical-chart/medical-chart.service";
import {CanActivateDoctorGuard} from "./guard/can-activate-doctor-guard";
import {MedicalChartDetailsComponent} from './component/medical_chart/medical-chart-details/medical-chart-details.component';
import {DiagnosticService} from "./service/diagnostic-service/diagnostic.service";
import {NotFoundPageComponent} from "./component/not-found-page/not-found-page.component";
import {DoctorFormComponent} from './component/doctors/doctor-form/doctor-form.component';
import {DoctorListComponent} from './component/doctors/doctor-list/doctor-list.component';
import {DoctorsPageComponent} from './component/doctors/doctors-page/doctors-page.component';
import {DoctorService} from "./service/doctor-service/doctor.service";


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    HomeComponent,
    MedicalChartListComponent,
    MedicalChartFormComponent,
    MedicalChartPageComponent,
    MedicalChartDetailsComponent,
    NotFoundPageComponent,
    DoctorFormComponent,
    DoctorListComponent,
    DoctorsPageComponent
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
    DiagnosticService,
    DoctorService,
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
