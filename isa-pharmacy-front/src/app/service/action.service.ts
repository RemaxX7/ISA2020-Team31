import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Action } from '../model/action.model';

@Injectable({
  providedIn: 'root'
})
export class ActionService {

  private readonly _APIUrl="http://localhost:8080//api/action"

  constructor(private _http : HttpClient ) { }

  addAction(action:Action):Observable<any>{
    return this._http.post(this._APIUrl+'/add',action);
  }


}
