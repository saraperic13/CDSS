import {RouterModule, Routes} from "@angular/router";
import {ModuleWithProviders} from "@angular/core";
import {LoginComponent} from "./component/login/login.component";
import {CanActivateAuthGuard} from "./guard/can-activate-auth-guard";
import {HomeComponent} from "./component/home/home.component";

const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [CanActivateAuthGuard]
  }

  //   {
  //     path: '**',
  //     component: NotFoundPageComponent
  //   }
];


export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);

