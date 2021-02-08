import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Country } from './country.model';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  readonly _APIUrl="http://localhost:8080/api/location"

  constructor(private _http : HttpClient) { }

  getAllCountries(): Observable<any> {
    return this._http.get(this._APIUrl + '/countries');
  }

  getAllCitiesByCountry(countryId:number): Observable<any> {
    return this._http.get(this._APIUrl + '/cities/' + countryId);
  }
}
