import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MedicineService {

  private readonly _APIUrl="http://localhost:8080/api/medicine"

  constructor(private _http : HttpClient ) { }

  getPage(page: number):Observable<any> {
    return this._http.get<any>(this._APIUrl + '/all/' + page);
  }

  getSearchResultPage(page: number, query:string):Observable<any> {
    return this._http.get<any>(this._APIUrl + '/search/' + query + '/' + page);
  }

  getAllMedicine(): Promise<any>{
    return this._http.get(this._APIUrl + '/all' ).toPromise();
  }
}
