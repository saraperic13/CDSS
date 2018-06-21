import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Symptom} from "../../domain/Symptom";

@Injectable()
export class SymptomService {

  private readonly path = 'http://localhost:8080/api/symptoms';

  constructor(private http: HttpClient) {
  }


  getAll(): Observable<Symptom[]> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

}
