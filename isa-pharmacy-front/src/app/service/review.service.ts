import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private readonly _APIUrl = "http://localhost:8080/api/reviews"

  constructor(private _http: HttpClient) { }

  getPharmacyRating(pharmacyId: number): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.get<any>(this._APIUrl + '/pharmacy/' + pharmacyId, options);
  }

  getMedicineRating(medicineId: number): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.get<any>(this._APIUrl + '/medicine/' + medicineId, options);
  }

  getEmployeeRating(employeeId: number): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.get<any>(this._APIUrl + '/employee/' + employeeId, options);
  }

  ratePharmacy(pharmacyReview: any): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.post<any>(this._APIUrl + '/pharmacy', pharmacyReview, options);
  }

  rateMedicine(medicineReview: any): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.post<any>(this._APIUrl + '/medicine', medicineReview, options);
  }

  rateEmployee(employeeReview: any): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.post<any>(this._APIUrl + '/employee', employeeReview, options);
  }
}
