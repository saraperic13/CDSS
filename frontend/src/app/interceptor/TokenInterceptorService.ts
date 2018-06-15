import {Injectable, Injector} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {AccountService} from "../service/account/account.service";

@Injectable()
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private inj: Injector) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>
  {
    let accountService : AccountService = this.inj.get(AccountService);

    request = request.clone(
      {
        setHeaders: {
          'authorization': `${accountService.getToken()}`
        }
      });
    console.log(request);
    return next.handle(request);
  }

}
