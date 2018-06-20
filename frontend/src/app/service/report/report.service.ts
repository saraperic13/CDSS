import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {MedicalChart} from "../../domain/MedicalChart";

@Injectable()
export class ReportService {

  private readonly path = 'http://localhost:8080/api/reports';

  constructor(private http: HttpClient) {
  }

  getChronic(): Observable<MedicalChart[]> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path + "/chronic", {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  getAddicted(): Observable<MedicalChart[]> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path + "/addicts", {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }
  getLowImuneSystem(): Observable<MedicalChart[]> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path + "/low_immune_system", {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

}
