import { RouterModule, Routes } from "@angular/router";
import { ModuleWithProviders } from "@angular/core";
import {LoginComponent} from "./component/login/login.component";
import {CanActivateAuthGuard} from "./guard/can-activate-auth-guard";
import {CanActivateAdminGuard} from "./guard/can-activate-admin-guard";

const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  }
  //   {
  //     path: '**',
  //     component: NotFoundPageComponent
  //   }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);

