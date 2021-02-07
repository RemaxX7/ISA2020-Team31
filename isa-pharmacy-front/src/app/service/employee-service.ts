import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })
  export class EmployeeService {
    readonly _APIUrl="http://localhost:8080/auth/search/employee"
  
    constructor(private _http : HttpClient) { }
    
    getAllUsers(): Promise<any>{
      return this._http.get(this._APIUrl + '/all' ).toPromise();
    }
    getAllCalendarEntries(forDays):Promise<any>{
      return this._http.get(this._APIUrl + '/pharmacistCalendarEntries').toPromise();
    }
    getById(id): Promise<any>{
      console.log(id);
      return this._http.get(this._APIUrl + '/getbyid/' + id).toPromise();
    }
  }