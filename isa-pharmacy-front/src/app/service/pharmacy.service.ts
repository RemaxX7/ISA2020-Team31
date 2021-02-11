import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {

  private readonly _APIUrl="http://localhost:8080/api/pharmacy"

  constructor(private _http : HttpClient ) { }

  getPharmacy(pharmacyId:number):Promise<any>{
    return this._http.get<any>(this._APIUrl+'/'+pharmacyId).toPromise();
  }

  getAll():Observable<any> {
    return this._http.get<any>(this._APIUrl + '/all')
  }

  getPage(page: number):Observable<any> {
    return this._http.get<any>(this._APIUrl + '/all/' + page);
  }

  getSearchResultPage(page: number, query:string):Observable<any> {
    return this._http.get<any>(this._APIUrl + '/search/' + query + '/' + page);
  }
}
