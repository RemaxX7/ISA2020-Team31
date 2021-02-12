import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private readonly _APIUrl = "http://localhost:8080/api/patients";

  constructor(private _http: HttpClient) { }

  getPatientProfile(): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.get<any>(this._APIUrl + '/profile', options);
  }

  getPatientAllergies():Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.get<any>(this._APIUrl + '/allergies', options);
  }

  updatePatientProfile(profile: any): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.post<any>(this._APIUrl + '/update/profile', profile, options);
  }

  updatePatientAllergies(allergies: any):Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.post<any>(this._APIUrl + '/update/allergies', allergies, options);
  }

  updatePassword(passwordChange: any): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.post<any>(this._APIUrl + '/update/password', passwordChange, options);
  }
}
