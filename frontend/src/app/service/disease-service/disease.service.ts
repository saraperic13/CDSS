import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Disease} from "../../domain/Disease";
import {Observable} from "rxjs/Observable";

@Injectable()
export class DiseaseService {

  private readonly path = 'http://localhost:8080/api/diseases';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Disease[]> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  add(diseaseParams: Disease): Observable<Disease[]> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(this.path, diseaseParams, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  delete(diseaseId: number) {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    console.log(JSON.stringify(diseaseId));
    return this.http.delete(this.path + "/" + diseaseId, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  update(disease: Disease) {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(this.path, disease, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

}
