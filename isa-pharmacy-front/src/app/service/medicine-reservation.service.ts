import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { promise } from "protractor";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MedicineReservationService {
  readonly _APIUrl = "http://localhost:8080/api/reservations"

  constructor(private _http: HttpClient) { }

  makeReservation(reservation: any): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.post(this._APIUrl + '/save', reservation, options)
  }

  getPage(page: number): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.get(this._APIUrl + '/created/' + page, options);
  }

  cancelReservation(reservationId: number): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.post(this._APIUrl + '/cancel/' + reservationId, {}, options);
  }
}