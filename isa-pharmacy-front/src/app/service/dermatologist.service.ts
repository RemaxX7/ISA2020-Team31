import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Dermatologist } from '../model/dermatologist.model';

@Injectable({
  providedIn: 'root'
})
export class DermatologistService {

  readonly _APIUrl="http://localhost:8080/api/dermatologist"

  constructor(private _http : HttpClient) { }

  FindAllByPharmacyId(pharmacyId:number):Observable<any>
  {
    return this._http.get<any>(this._APIUrl+'/all/'+pharmacyId);
  }
  FindAll():Promise<Dermatologist[]>
  {
    return this._http.get<Dermatologist[]>(this._APIUrl+'/all').toPromise();
  }
}
