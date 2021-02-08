import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PharmacistService {

  private readonly _APIUrl="http://localhost:8080/api/pharmacist";

  constructor(private _http : HttpClient) { }

  FindAllByPharmacyId(pharmacyId:number):Observable<any>
  {
    return this._http.get<any>(this._APIUrl+'/all/'+pharmacyId);
  }

  FindAll():Observable<any>
  {
    return this._http.get<any>(this._APIUrl+'/all');
  }
}
