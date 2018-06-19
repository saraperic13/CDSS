import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/";
import {MedicalChart} from "../../domain/MedicalChart";
import {Injectable} from "@angular/core";
import {Doctor} from "../../domain/Doctor";

@Injectable()
export class DoctorService {

  private readonly path = 'http://localhost:8080/api/doctors';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Doctor[]> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  getOne(id: number): Observable<Doctor> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path + "/" + id, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  add(doctorParams: Doctor): Observable<Doctor> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(this.path, doctorParams, {headers}).map((response: any) => {
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

  update(doctor: Doctor) {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(this.path, doctor, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }
}
