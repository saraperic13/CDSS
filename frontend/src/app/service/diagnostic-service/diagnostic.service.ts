import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/";
import {MedicalChart} from "../../domain/MedicalChart";
import {Symptom} from "../../domain/Symptom";
import {Disease} from "../../domain/Disease";
import {Diagnosis} from "../../domain/Diagnosis";

@Injectable()
export class DiagnosticService {

  private readonly path = 'http://localhost:8080/api/diagnostic_process';

  constructor(private http: HttpClient) {
  }

  diagnose(symptoms: Symptom[], chartId: number): Observable<Disease> {
    console.log(symptoms);
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(this.path + "/calculate/" + chartId, symptoms, {headers}).map((response: any) => {
      return response;
    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  setDiagnosis(diagnosis: Diagnosis, chartId: number): Observable<Disease> {
    console.log(diagnosis);
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(this.path + "/" + chartId, diagnosis, {headers}).map((response: any) => {
      return response;
    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }
}
