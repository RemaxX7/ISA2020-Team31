import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { PharmacyService } from 'src/app/service/pharmacy.service';

@Component({
  selector: 'app-patient-pharmacies',
  templateUrl: './patient-pharmacies.component.html',
  styleUrls: ['./patient-pharmacies.component.css']
})
export class PatientPharmaciesComponent implements OnInit {

  constructor(private pharmacyService: PharmacyService, private toastr: ToastrService) { }

  public pharmacies:Array<any>;
  public totalElements:number;
  private searchActive:boolean = false;
  public ratingDisabled = false;
  public distanceDisabled = false;

  ngOnInit(): void {
    this.GetPharmacies(0);
  }

  GetPharmacies(page: number) {
    this.pharmacyService.getPage(page).subscribe(
      data => {
        this.pharmacies = data['content'];
        this.totalElements = data['totalElements'];
        this.searchActive = false;
      }
    )
  }

  Search() {
    let query = (<HTMLInputElement>document.getElementById('search-input')).value;
    if(!query) {
      this.GetPharmacies(0);
    } else {
      this.SearchPharmacies(0, query);
    }
  }

  SearchPharmacies(page: number, query: string) {
    this.pharmacyService.getSearchResultPage(page, query).subscribe(
      data => {
        this.pharmacies = data['content'];
        this.totalElements = data['totalElements'];
        this.searchActive = true;
      },
      err => this.toastr.error(err.error)
    )
  }

  NextPage(event: PageEvent) {
    let query = (<HTMLInputElement>document.getElementById('search-input')).value;
    if(query) {
      this.SearchPharmacies(event.pageIndex, query);
    } else {
      this.GetPharmacies(event.pageIndex);
    }
  }

  CheckIfEmpty(event: InputEvent){
    if(!(<HTMLInputElement>document.getElementById('search-input')).value && this.searchActive) {
      this.GetPharmacies(0);
    }
  }
}
