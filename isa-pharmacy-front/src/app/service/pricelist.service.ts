import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PricelistItemMedicineCreate } from '../dto/pricelist-item-medicine-create.model';
import { SetMedicinePrice } from '../dto/set-medicine-price.model';

@Injectable({
  providedIn: 'root'
})
export class PricelistService {

  private readonly _APIUrl="http://localhost:8080/auth/pricelist"

  constructor(private _http : HttpClient ) { }

  GetPricelistByPharmacyId(pharmacyId:number):Observable<any>{
    return this._http.get(this._APIUrl+'/'+pharmacyId);
  }

  GetAllActiveMedicineItem(pricelitsId:number):Observable<any>{
    return this._http.get(this._APIUrl+'/'+pricelitsId+'/allMedicine');
  }

  SetNewMedicinePrice(dto:SetMedicinePrice):Observable<any>
  {
    return this._http.post(this._APIUrl+"/item/setNewPrice",dto);
  }

}
