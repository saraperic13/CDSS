import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Rule} from "../../domain/Rule";

@Injectable()
export class RuleService {

  private readonly path = 'http://localhost:8080/api/rules';

  constructor(private http: HttpClient) {
  }

  add(rule: Rule): Observable<boolean> {
    console.log(rule);

    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(this.path, rule, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }
}
