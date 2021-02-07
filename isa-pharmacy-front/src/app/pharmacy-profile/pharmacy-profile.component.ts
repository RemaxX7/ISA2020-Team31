import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Address } from '../model/address.model';
import { City } from '../model/city.model';
import { Dermatologist } from '../model/dermatologist.model';
import { Medicine } from '../model/medicine.model';
import { Pharmacist } from '../model/pharmacist.model';
import { Pharmacy } from '../model/pharmacy.model';


@Component({
  selector: 'app-pharmacy-profile',
  templateUrl: './pharmacy-profile.component.html',
  styleUrls: ['./pharmacy-profile.component.css']
})
export class PharmacyProfileComponent implements OnInit {

  constructor(public dialog: MatDialog) { }
  pharmacy:Pharmacy=new Pharmacy;
  currentRate:number;
  dermatologists:Dermatologist[]=[];
  pharmacists:Pharmacist[]=[];
  medicines:Medicine[]=[];
  
  
  medicine:Medicine=new Medicine;
  dermatologist:Dermatologist=new Dermatologist;
  pharmacist:Pharmacist=new Pharmacist;

  ngOnInit(): void {
    this.Test();
    
    this.currentRate = this.pharmacy.Rate;
    
    
  }

  CheckAvailableAppointments(dermatologistId:number){
  }

  ScheduleConsultation(pharmacistId:number){
    
    
  }

  

  Test(){
    this.pharmacy.Name="Jankovic Apoteka"
    this.pharmacy.Address=new Address;
    this.pharmacy.Address.Street="Bulevar Mihajla Pupuna";
    this.pharmacy.Address.Number=10;
    this.pharmacy.Address.City=new City;
    this.pharmacy.Address.City.Name="Novi Sad";
    this.pharmacy.Rate=4.5;
    this.dermatologist.id=1;
    this.dermatologist.name="Dragan Draganic";
    this.dermatologist.email="dragandraganic@gmail.com";
    this.dermatologist.pharmacies=[];
    this.dermatologist.pharmacies.push(this.pharmacy);
    this.dermatologists.push(this.dermatologist);
    this.dermatologists.push(this.dermatologist);
    this.dermatologists.push(this.dermatologist);
    this.pharmacist.id=2;
    this.pharmacist.name="Nikola Nikolic";
    this.pharmacist.email="nikolicnikola@gmail.com";
    this.pharmacist.pharmacies=this.pharmacy;
    this.pharmacists.push(this.pharmacist);
    this.pharmacists.push(this.pharmacist);
    this.pharmacists.push(this.pharmacist);
    this.medicine.Id=1;
    this.medicine.Name="Brufen";
    this.medicine.Manufacturer="Abbott";
    this.medicines.push(this.medicine);
    this.medicines.push(this.medicine);
    this.medicines.push(this.medicine);
  }

}
