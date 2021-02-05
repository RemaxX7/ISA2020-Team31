import { Component, OnInit } from '@angular/core';
import { Pharmacist } from '../model/pharmacist.model';

@Component({
  selector: 'app-pharmacist-profile',
  templateUrl: './pharmacist-profile.component.html',
  styleUrls: ['./pharmacist-profile.component.css']
})
export class PharmacistProfileComponent implements OnInit {
  
  constructor() { }
  pharmacists:Pharmacist[]=[]

  pharmacist:Pharmacist = new Pharmacist;

  ngOnInit(): void {
    this.Test();
  }
  Test(){
    this.pharmacist.name = "Aleksa";
    this.pharmacist.surname = "Rakic"
    this.pharmacist.id = 22222;
    this.pharmacist.email = "ar95@email.com";
  }
}
