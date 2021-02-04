import { Component, OnInit } from '@angular/core';
import { Dermatologist } from '../model/dermatologist.model';

@Component({
  selector: 'app-dermatologist-profile',
  templateUrl: './dermatologist-profile.component.html',
  styleUrls: ['./dermatologist-profile.component.css']
})
export class DermatologistProfileComponent implements OnInit {

  constructor() { }
  dermatologists:Dermatologist[]=[]

  dermatologist:Dermatologist = new Dermatologist;
  ngOnInit(): void {
    this.Test();
  }
  Test(){
    this.dermatologist.Name ="Marko";
    this.dermatologist.Email = "mr98@gmail.com";
    this.dermatologist.Id = 11111;
    this.dermatologist.Surname = "Rakic";
    this.dermatologists.push(this.dermatologist);
  }
}
