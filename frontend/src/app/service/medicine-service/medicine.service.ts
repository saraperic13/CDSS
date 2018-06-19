import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Medicine} from "../../domain/Medicine";

@Injectable()
export class MedicineService {

  private readonly path = 'http://localhost:8080/api/medicines';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Medicine[]> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  getOne(medicineId: number): Observable<Medicine> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path + "/" + medicineId, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  add(medicineParams: Medicine): Observable<Medicine[]> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(this.path, medicineParams, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  delete(medicineId: number) {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    console.log(JSON.stringify(medicineId));
    return this.http.delete(this.path + "/" + medicineId, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  update(medicine: Medicine) {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(this.path, medicine, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

}
