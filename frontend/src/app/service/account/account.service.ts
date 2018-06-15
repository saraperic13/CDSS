import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import 'rxjs/Rx';
import {Observable} from "rxjs";

@Injectable()
export class AccountService {

  private readonly basePath = 'http://localhost:8080/api/account/';

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string): Observable<boolean> {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    let param = JSON.stringify({username, password});

    return this.http.post(this.basePath + "login", param, {headers}).map((res: any) => {

      localStorage.removeItem('currentAccount');
      let token = res && res.token;
      if (token) {

        localStorage.setItem('currentAccount', JSON.stringify(
          {
            username: username,
            token: token,
            roles: this.getRoles(token)
          }));

        return true;
      }
      else {
        return false;
      }
    })
      .catch((error: any) => {
        if (error.status === 400)
          return Observable.throw('Illegal login');
        else if (error.status == 403)
          return Observable.throw('Account blocked');
        else
          return Observable.throw(error.name || 'Server error');
      });
  }

  logout(): void {
    localStorage.removeItem('currentAccount');
  }

  getCurrentAccount() {
    if (localStorage.currentAccount)
      return JSON.parse(localStorage.currentAccount);
    else
      return undefined;
  }

  isLoggedIn(): boolean {
    return this.getToken() != '';
  }

  private getRoles(token: string) {
    let jwtData = token.split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let claims = JSON.parse(decodedJwtJsonData);
    return claims.roles;
  }

  getToken(): string {
    let currentAccount = JSON.parse(localStorage.getItem('currentAccount'));
    let token = currentAccount && currentAccount.token;
    return token ? token : "";
  }

}
