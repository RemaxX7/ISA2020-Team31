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
    this.GetPharmacists();
  }

  GetPharmacists()
  {
    this.pharmacistService.FindAll().subscribe(
      data=>{
        this.pharmacists=data;
        
      }
    )
  }

}
