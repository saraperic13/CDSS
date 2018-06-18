import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/";
import {MedicalChart} from "../../domain/MedicalChart";
import {Diagnosis} from "../../domain/Diagnosis";
import {Symptoms} from "../../domain/Symptoms";

@Injectable()
export class DiagnosticService {

  private readonly path = 'http://localhost:8080/api/diagnostic_process';

  constructor(private http: HttpClient) {
  }

  diagnose(symptoms: Symptoms): Observable<Diagnosis> {
    console.log(symptoms);
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(this.path, symptoms, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }
}
