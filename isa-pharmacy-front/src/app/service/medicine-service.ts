import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { promise } from "protractor";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
  })
  export class MedicineService {
    readonly _APIUrl="http://localhost:8080/api/medicine"
  
    constructor(private _http : HttpClient) { }
    
    getAllMedicineForPatient(userid): Promise<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get(this._APIUrl + '/medicineforpatient/'+ userid,options ).toPromise();
    }
    getAllMedicine(): Promise<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
        return this._http.get(this._APIUrl + '/all',options ).toPromise();
      }
    getCompositionForMedicine(name):Promise<any>{
      let headers = new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
      return this._http.get(this._APIUrl + '/composition/'+name,options ).toPromise();
    }
  }