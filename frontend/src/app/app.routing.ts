import {RouterModule, Routes} from "@angular/router";
import {ModuleWithProviders} from "@angular/core";
import {LoginComponent} from "./component/login/login.component";
import {CanActivateAuthGuard} from "./guard/can-activate-auth-guard";
import {HomeComponent} from "./component/home/home.component";
import {MedicalChartPageComponent} from "./component/medical_chart/medical-chart-page/medical-chart-page.component";
import {CanActivateDoctorGuard} from "./guard/can-activate-doctor-guard";
import {MedicalChartDetailsComponent} from "./component/medical_chart/medical-chart-details/medical-chart-details.component";
import {NotFoundPageComponent} from "./component/not-found-page/not-found-page.component";
import {DoctorsPageComponent} from "./component/doctors/doctors-page/doctors-page.component";
import {CanActivateAdminGuard} from "./guard/can-activate-admin-guard";
import {MedicinePageComponent} from "./component/medicine/medicine-page/medicine-page.component";

const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [CanActivateAuthGuard]
  },
  {
    path: 'medical_charts',
    component: MedicalChartPageComponent,
    canActivate: [CanActivateDoctorGuard]
  },
  {
    path: 'doctors',
    component: DoctorsPageComponent,
    canActivate: [CanActivateAdminGuard]
  },
  {
    path: 'medicines',
    component: MedicinePageComponent,
    canActivate: [CanActivateAdminGuard]
  },
  {
    path: 'medical_charts/:chartId',
    component: MedicalChartDetailsComponent,
    canActivate: [CanActivateDoctorGuard]
  },
  {
    path: '**',
    component: NotFoundPageComponent
  }
];


export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);

