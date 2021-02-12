import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InventoryItemCreate } from '../dto/inventory-item-create.model';

@Injectable({
  providedIn: 'root'
})
export class PharmacyService {

  private readonly _APIUrl = "http://localhost:8080/api/pharmacy"

  constructor(private _http: HttpClient) { }

  getPharmacy(pharmacyId: number): Promise<any> {
    return this._http.get<any>(this._APIUrl + '/' + pharmacyId).toPromise();
  }

  getAll(): Observable<any> {
    return this._http.get<any>(this._APIUrl + '/all')
  }

  getPage(page: number): Observable<any> {
    return this._http.get<any>(this._APIUrl + '/all/' + page);
  }

  getSearchResultPage(page: number, query: string): Observable<any> {
    return this._http.get<any>(this._APIUrl + '/search/' + query + '/' + page);
  }

  getAvailablePharmacies(dateTime: Date): Observable<any> {
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('userToken')
    })
    let options = { headers: headers };
    return this._http.post<any>(this._APIUrl + '/available/counseling', dateTime, options);
  }
  
  addItem(dto: InventoryItemCreate): Observable<any> {
    return this._http.post(this._APIUrl + '/addItem', dto);
  }
}
