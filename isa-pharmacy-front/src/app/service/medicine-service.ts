import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
  })
  export class MedicineService {
    readonly _APIUrl="http://localhost:8080/auth/medicine"
  
    constructor(private _http : HttpClient) { }
    
    getAllMedicineForPatient(userid): Promise<any>{
      return this._http.get(this._APIUrl + '/medicineforpatient/'+ userid ).toPromise();
    }
    getAllMedicine(): Promise<any>{
        return this._http.get(this._APIUrl + '/all' ).toPromise();
      }
  }