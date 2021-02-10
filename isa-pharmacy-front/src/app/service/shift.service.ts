import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DateRange } from '../model/date-range.model';
import { ShiftDto } from '../model/shift-dto.model';

@Injectable({
  providedIn: 'root'
})
export class ShiftService {

  private readonly _APIUrl="http://localhost:8080/auth/shift"

  constructor(private _http : HttpClient ){}

  AddShift(dto:ShiftDto):Observable<any>{
    return this._http.post(this._APIUrl+'/add',dto);
  }
}
