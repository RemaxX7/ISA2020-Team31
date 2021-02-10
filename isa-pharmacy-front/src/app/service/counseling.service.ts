import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CounselingService {

  private readonly _APIUrl = "http://localhost:8080/api/appointments/counselings"

  constructor(private _http: HttpClient) { }

  getPageFinished(page: number, sort: string): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.get<any>(this._APIUrl + '/finished/' + page + '/' + sort, options);
  }

  getPageCreated(page: number, sort: string): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.get<any>(this._APIUrl + '/created/' + page + '/' + sort, options);
  }
  cancel(id: number) {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.post<any>(this._APIUrl + '/cancel/' + id, {}, options);
  }
}
