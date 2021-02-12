import { Component, OnInit } from '@angular/core';
import { Dermatologist } from '../model/dermatologist.model';
import { Pharmacy } from '../model/pharmacy.model';
import { DermatologistService } from '../service/dermatologist.service';

@Component({
  selector: 'app-dermatologist-list',
  templateUrl: './dermatologist-list.component.html',
  styleUrls: ['./dermatologist-list.component.css']
})
export class DermatologistListComponent implements OnInit {

  constructor(private dermatologistService:DermatologistService) { }

  dermatologists:Dermatologist[]=[];
  

  ngOnInit(): void {
  }

  async GetDermatologists()
  {
    let d:Dermatologist[];
    await this.dermatologistService.FindAll().then(
      data=>{
        this.dermatologists=data;
      }
    );
  }

  searchActive:boolean;
  Search() {
    let query = (<HTMLInputElement>document.getElementById('search-input')).value;
    if (query) {
      this.SearchDermatologist( query);
    }
  }

  SearchDermatologist( query: string) {
    this.dermatologistService.search( query).subscribe(
      data => {
        this.dermatologists = data;
        this.searchActive = true;
      }
    )
  }

  CheckIfEmpty(event: InputEvent) {
      this.GetDermatologists();
    }




}
