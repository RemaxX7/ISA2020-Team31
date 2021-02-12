import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppointmentReportPharmacistComponent } from '../appointment-report-pharmacist/appointment-report-pharmacist.component';
import { Appointment } from '../model/appointment.model';
import { PharmacistAppointmentsPageComponent } from '../pharmacist-appointments-page/pharmacist-appointments-page.component';
import { Pharmacist } from '../model/pharmacist.model';

@Injectable({
    providedIn: 'root'
  })
  export class EmployeeService {
    readonly _APIUrl="http://localhost:8080/api/search/employee"
  
    constructor(private _http : HttpClient) { }
    
    getAllUsers(): Promise<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get("http://localhost:8080/api/patients/all",options).toPromise();
    }
    fillExams(): Promise<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get("http://localhost:8080/api/appointments/exams/allactive",options).toPromise();
    }
    fillCounselings(): Promise<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get("http://localhost:8080/api/appointments/counselings/allactive",options).toPromise();
    }
    loadReservation(reservation,uidn):Observable<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get("http://localhost:8080/api/search/employee/reservation/" + reservation+"/"+uidn,options);
    }
    finalizeReservation(code):Observable<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get("http://localhost:8080/api/reservations/pickedup/"+code,options);
    }
    getById(id): Promise<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get(this._APIUrl + '/getbyid/' + id,options).toPromise();
    }
    getByExamId(examid):Promise<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get("http://localhost:8080/api/appointments/exams/findbyid/" + examid,options).toPromise();
    }
    getByCounselingId(examid):Promise<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get("http://localhost:8080/api/appointments/counselings/findbyid/" + examid,options).toPromise();
    }
    penalizePatient(uidn):Promise<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get("http://localhost:8080/api/appointments/exams/penalize/" + uidn,options).toPromise();
    }
    penalizePatientPharmacist(uidn):Promise<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get("http://localhost:8080/api/appointments/counselings/pharmacistpenalize/" + uidn,options).toPromise();
    }
    sendAppointmentDTO(val:Appointment,quant):Observable<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.post("http://localhost:8080/api/appointments/exams/finalizeappointment/"+quant,val,options);
    }
    sendAppointmentDTOPharmacist(val:Appointment,quant):Observable<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.post("http://localhost:8080/api/appointments/counselings/finalizeappointmentpharmacist/"+quant,val,options);
    }
    scheduleNewAppointmentDerm(val:Appointment):Observable<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.post("http://localhost:8080/api/appointments/exams/schedulenewexam",val,options);
    }
    scheduleNewAppointmentPharm(val:Appointment):Observable<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.post("http://localhost:8080/api/appointments/counselings/schedulenewcounseling",val,options);
    }
    editProfile(val:any):Observable<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.post("http://localhost:8080/api/search/employee/editprofile",val,options);
    }
    editPassword(val:any):Observable<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.post("http://localhost:8080/api/search/employee/editpassword",val,options);
    }
    getFreeTermins(patiudn,empuidn):Observable<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get("http://localhost:8080/api/search/employee/freeterm/" + patiudn + "/" + empuidn,options);
    }
    getFreeTerminsPharm(patiudn,empuidn):Observable<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get("http://localhost:8080/api/search/employee/freetermpharm/" + patiudn + "/" + empuidn,options);
    }
    getConsultationsForPharmacist(uidn,days):Observable<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get("http://localhost:8080/api/search/employee/counsforpharm/" + uidn+"/"+days,options);
    }
    getExamsForDermatologist(uidn,days):Observable<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get("http://localhost:8080/api/search/employee/examsforderm/" + uidn+"/"+days,options);
    }
    medicineAvailability(name,id):Promise<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get("http://localhost:8080/api/search/employee/medicineavailability/" + name+"/"+id,options).toPromise();
    }

    getAllEmployees():Observable<any> {
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
      let options = { headers: headers };
      return this._http.get(this._APIUrl + '/employees', options);
    }
  }