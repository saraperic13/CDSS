import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/";
import {MedicalChart} from "../../domain/MedicalChart";
import {Diagnosis} from "../../domain/Diagnosis";
import {Symptoms} from "../../domain/Symptoms";

@Injectable()
export class MedicalChartService {

  private readonly path = 'http://localhost:8080/api/medical_charts';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<MedicalChart[]> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  getOne(chartId: number): Observable<MedicalChart> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path + "/" + chartId, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  add(chartParams: MedicalChart): Observable<MedicalChart[]> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(this.path, chartParams, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  delete(chartId: number) {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    console.log(JSON.stringify(chartId));
    return this.http.delete(this.path + "/" + chartId, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  update(chart: MedicalChart) {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(this.path, chart, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }
}
