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
}
