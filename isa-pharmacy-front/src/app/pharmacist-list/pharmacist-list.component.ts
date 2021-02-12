import { Component, OnInit } from '@angular/core';
import { Pharmacist } from '../model/pharmacist.model';
import { PharmacistService } from '../service/pharmacist.service';

@Component({
  selector: 'app-pharmacist-list',
  templateUrl: './pharmacist-list.component.html',
  styleUrls: ['./pharmacist-list.component.css']
})
export class PharmacistListComponent implements OnInit {

  constructor(private pharmacistService:PharmacistService) { }

  pharmacists:Pharmacist[]=[];
  ngOnInit(): void {
    this.searchActive=false;
  }

  GetPharmacists()
  {
    this.pharmacistService.FindAll().subscribe(
      data=>{
        this.pharmacists=data;
        
      }
    )
  }
  searchActive:boolean;
  Search() {
    let query = (<HTMLInputElement>document.getElementById('search-input')).value;
    if (query) {
      this.SearchPharmacist( query);
    }
  }

  SearchPharmacist( query: string) {
    this.pharmacistService.search( query).subscribe(
      data => {
        this.pharmacists = data;
        this.searchActive = true;
      }
    )
  }

  CheckIfEmpty(event: InputEvent) {
      this.GetPharmacists();
    }

}
