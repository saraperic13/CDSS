import {Component, OnInit} from '@angular/core';
import {AccountService} from "../../service/account/account.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user;

  public wrongUsernameOrPass: boolean;

  public accountBlocked: boolean;

  constructor(private accountService: AccountService,
              private router: Router) {
    this.user = {};
  }

  ngOnInit() {
    this.accountService.logout();
  }

  login(): void {
    this.accountService.login(this.user.username, this.user.password).subscribe(
      (loggedIn: boolean) => {
        console.log("EEEVO MEEE");
        if (loggedIn) {
          this.router.navigate(['/home']);
        }
      }
      ,
      (err: Error) => {
        if (err.toString() === 'Illegal login') {
          this.wrongUsernameOrPass = true;
          this.accountBlocked = false;
        }
        else if (err.toString() === 'Account blocked') {
          this.accountBlocked = true;
          this.wrongUsernameOrPass = false;
        }
        else {
          Observable.throw(err);
        }
      });
  }

}
