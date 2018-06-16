import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Router} from '@angular/router/';
import {Observable} from 'rxjs/Observable';
import {AccountService} from "../service/account/account.service";
import {RoleType} from "../domain/RoleType";

@Injectable()
export class CanActivateDoctorGuard implements CanActivate {

  constructor(private accountService: AccountService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if (this.accountService.isLoggedIn() && this.userIsDoctor()) {
      return true;
    }
    else {
      this.router.navigate(['/login']);
      return false;
    }
  }

  userIsDoctor(){
    let roles =this.accountService.getCurrentAccount().roles;
    for(let role of roles){
      if(role.toLowerCase() === RoleType.DOCTOR.toString().toLowerCase()){
        return true;
      }
    }
    return false;
  }

}
