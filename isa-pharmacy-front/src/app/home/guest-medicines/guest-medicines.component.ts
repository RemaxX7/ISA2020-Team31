import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { MedicineService } from 'src/app/service/medicine.service';

@Component({
  selector: 'app-guest-medicines',
  templateUrl: './guest-medicines.component.html',
  styleUrls: ['./guest-medicines.component.css']
})
export class GuestMedicinesComponent implements OnInit {

  public medicines:Array<any>;
  public totalElements:number;
  private searchActive:boolean = false;

  constructor(private medicineService: MedicineService) { }

  ngOnInit(): void {
    this.GetMedicines(0);
  }

  GetMedicines(page: number) {
    this.medicineService.getPage(page).subscribe(
      data => {
        console.log(data);
        this.medicines = data['content'];
        this.totalElements = data['totalElements'];
        this.searchActive = false;
      }
    )
  }

  Search() {
    let query = (<HTMLInputElement>document.getElementById('search-input')).value;
    if(!query) {
      this.GetMedicines(0);
    } else {
      this.SearchMedicines(0, query);
    }
  }

  SearchMedicines(page: number, query: string) {
    this.medicineService.getSearchResultPage(page, query).subscribe(
      data => {
        this.medicines = data['content'];
        this.totalElements = data['totalElements'];
        this.searchActive = true;
      }
    )
  }

  NextPage(event: PageEvent) {
    let query = (<HTMLInputElement>document.getElementById('search-input')).value;
    if(query) {
      this.SearchMedicines(event.pageIndex, query);
    } else {
      this.GetMedicines(event.pageIndex);
    }
  }

  CheckIfEmpty(event: InputEvent){
    if(!(<HTMLInputElement>document.getElementById('search-input')).value && this.searchActive) {
      this.GetMedicines(0);
    }
  }
}
