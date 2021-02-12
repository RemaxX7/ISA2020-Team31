import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MedicineService } from 'src/app/service/medicine.service';
import { RatingDialogComponent } from '../rating-dialog/rating-dialog.component';

@Component({
  selector: 'app-patient-medicines',
  templateUrl: './patient-medicines.component.html',
  styleUrls: ['./patient-medicines.component.css']
})
export class PatientMedicinesComponent implements OnInit {

  public medicines:Array<any>;
  public totalElements:number;
  private searchActive:boolean = false;

  constructor(private medicineService: MedicineService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.GetMedicines(0);
  }

  GetMedicines(page: number) {
    this.medicineService.getPage(page).subscribe(
      data => {
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

  OpenDialog(medicineId) {
    this.dialog.open(RatingDialogComponent, {
      data: {
        type: 'medicine',
        target: medicineId
      }
    });
  }
}