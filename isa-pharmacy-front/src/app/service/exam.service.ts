import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExamService {

  private readonly _APIUrl = "http://localhost:8080/api/appointments/exams"

  constructor(private _http: HttpClient) { }

  getPageFree(page: number, sort: string): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.get<any>(this._APIUrl + '/free/' + page + '/' + sort, options);
  }

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

  schedule(id: number) {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.post<any>(this._APIUrl + '/schedule/' + id, {}, options);
  }

  cancel(id: number) {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.post<any>(this._APIUrl + '/cancel/' + id, {}, options);
  }
}
