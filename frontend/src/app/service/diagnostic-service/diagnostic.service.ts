import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/";
import {MedicalChart} from "../../domain/MedicalChart";
import {Symptom} from "../../domain/Symptom";
import {Disease} from "../../domain/Disease";
import {Diagnosis} from "../../domain/Diagnosis";
import {Medicine} from "../../domain/Medicine";

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

  setDiagnosis(diagnosis: Diagnosis, chartId: number): Observable<Diagnosis> {
    console.log(diagnosis);
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(this.path + "/" + chartId, diagnosis, {headers}).map((response: any) => {
      return response;
    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  getAll(): Observable<Diagnosis[]> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  delete(id: number) {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    console.log(JSON.stringify(id));
    return this.http.delete(this.path + "/" + id, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  getAllForChart(chartId: number) {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path + "/" + chartId, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  validateMedicines(medicines: Medicine[], chartId: number): Observable<boolean> {
    console.log(medicines);
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(this.path + "/validate_medicines/" + chartId, medicines,
      {headers}).map((response: any) => {
      return response;
    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  setMedicines(medicines: Medicine[], diagnosisId: number) {
    console.log(medicines);
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(this.path + "/prescribe/" + diagnosisId, medicines,
      {headers}).map((response: any) => {
      return response;
    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }
}
