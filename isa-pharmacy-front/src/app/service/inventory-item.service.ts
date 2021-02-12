import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InventoryItemService {

  private readonly _APIUrl="http://localhost:8080/api/inventory"

  constructor(private _http : HttpClient ) { }
}
