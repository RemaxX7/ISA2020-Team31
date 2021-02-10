import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppointmentReportPharmacistComponent } from '../appointment-report-pharmacist/appointment-report-pharmacist.component';
import { Appointment } from '../model/appointment.model';
import { PharmacistAppointmentsPageComponent } from '../pharmacist-appointments-page/pharmacist-appointments-page.component';
import { Pharmacist } from '../model/pharmacist.model';

@Injectable({
    providedIn: 'root'
  })
  export class EmployeeService {
    readonly _APIUrl="http://localhost:8080/auth/search/employee"
  
    constructor(private _http : HttpClient) { }
    
    getAllUsers(): Promise<any>{
      return this._http.get("http://localhost:8080/auth/search/patients/all").toPromise();
    }
    getAllCalendarEntries(forDays):Promise<any>{
      return this._http.get(this._APIUrl + '/pharmacistCalendarEntries').toPromise();
    }
    getById(id): Promise<any>{
      return this._http.get(this._APIUrl + '/getbyid/' + id).toPromise();
    }
    penalizePatient(uidn):Promise<any>{
      return this._http.get("http://localhost:8080/auth/appointments/exams/penalize/" + uidn).toPromise();
    }
    penalizePatientPharmacist(uidn):Promise<any>{
      return this._http.get("http://localhost:8080/auth/appointments/counselings/pharmacistpenalize/" + uidn).toPromise();
    }
    sendAppointmentDTO(val:Appointment):Observable<any>{
      return this._http.post("http://localhost:8080/auth/appointments/exams/finalizeappointment",val);
    }
    sendAppointmentDTOPharmacist(val:Appointment):Observable<any>{
      return this._http.post("http://localhost:8080/auth/appointments/counselings/finalizeappointmentpharmacist",val);
    }
    scheduleNewAppointmentDerm(val:Appointment):Observable<any>{
      return this._http.post("http://localhost:8080/auth/appointments/exams/schedulenewexam",val);
    }
    scheduleNewAppointmentPharm(val:Appointment):Observable<any>{
      return this._http.post("http://localhost:8080/auth/appointments/counselings/schedulenewcounseling",val);
    }
    editProfile(val:any):Observable<any>{
      return this._http.post("http://localhost:8080/auth/search/employee/editprofile",val);
    }
  }