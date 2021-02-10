import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PharmacyComplaintDTO } from '../patient/dto/pharmacy-complaint-dto.model';
import { EmployeeComplaintDTO } from '../patient/dto/employee-complaint-dto.model';

@Injectable({
    providedIn: 'root'
})
export class ComplaintService {

    private readonly _APIUrl = "http://localhost:8080/api/complaints"

    constructor(private _http: HttpClient) { }

    makePharmacyComplaint(complaint: PharmacyComplaintDTO): Observable<any> {
        let headers = new HttpHeaders({
            'Authorization': 'Bearer ' + localStorage.getItem('userToken')
        })
        let options = { headers: headers };
        return this._http.post<any>(this._APIUrl + '/pharmacy/save', complaint, options)
    }

    makeEmployeeComplaint(complaint: EmployeeComplaintDTO): Observable<any> {
        let headers = new HttpHeaders({
            'Authorization': 'Bearer ' + localStorage.getItem('userToken')
        })
        let options = { headers: headers };
        return this._http.post<any>(this._APIUrl + '/employee/save', complaint, options)
    }
}
