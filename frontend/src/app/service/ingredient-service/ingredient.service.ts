import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Ingredient} from "../../domain/Ingredient";

@Injectable()
export class IngredientService {

  private readonly path = 'http://localhost:8080/api/ingredients';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Ingredient[]> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  getOne(ingredientId: number): Observable<Ingredient> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.get(this.path + "/" + ingredientId, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  add(ingredientParams: Ingredient): Observable<Ingredient[]> {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(this.path, ingredientParams, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  delete(ingredientId: number) {

    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    console.log(JSON.stringify(ingredientId));
    return this.http.delete(this.path + "/" + ingredientId, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

  update(ingredient: Ingredient) {
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put(this.path, ingredient, {headers}).map((response: any) => {
      return response;

    }).catch((error: any) => {
      return Observable.throw(error || 'Server error');
    });
  }

}
