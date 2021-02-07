import { Component, OnInit } from '@angular/core';
import { Dermatologist } from '../model/dermatologist.model';
import { Medicine } from '../model/medicine.model';
import { Pharmacist } from '../model/pharmacist.model';
import { Pharmacy } from '../model/pharmacy.model';
import { DermatologistService } from '../service/dermatologist.service';
import { PharmacistService } from '../service/pharmacist.service';
import { PharmacyService } from '../service/pharmacy.service';


@Component({
  selector: 'app-pharmacy-profile',
  templateUrl: './pharmacy-profile.component.html',
  styleUrls: ['./pharmacy-profile.component.css']
})
export class PharmacyProfileComponent implements OnInit {

  constructor(private pharmacyService:PharmacyService,private pharmacistService:PharmacistService,private dermatologistService:DermatologistService) { }
  pharmacy:Pharmacy=new Pharmacy;
  currentRate:number;
  dermatologists:Dermatologist[]=[];
  pharmacists:Pharmacist[]=[];
  medicines:Medicine[]=[];
  
  
  medicine:Medicine=new Medicine;
  dermatologist:Dermatologist=new Dermatologist;
  pharmacist:Pharmacist=new Pharmacist;

  ngOnInit(): void {
    this.GetPharmacy(1);
    this.GetPharmacists(1);
    this.GetDermatologists(1);
    this.Test();
   
    
  }

  async GetPharmacy(pharmacyId:number){
    await this.pharmacyService.getPharmacy(pharmacyId).then(data=>{
          this.pharmacy=data;
          this.currentRate = this.pharmacy.rate;
        }
      )
  }

 GetPharmacists(pharmacyId:number)
  {
    this.pharmacistService.FindAllByPharmacyId(pharmacyId).subscribe(
      data=>{
        this.pharmacists=data;
        
      }
    )
  }

  GetDermatologists(pharmacyId:number)
  {
    this.dermatologistService.FindAllByPharmacyId(pharmacyId).subscribe(
      data=>{
        this.dermatologists=data;
        
      }
    )
  }

  CheckAvailableAppointments(dermatologistId:number){
  }

  ScheduleConsultation(pharmacistId:number){
    
    
  }

  

  Test(){
    this.medicine.id=1;
    this.medicine.name="Brufen";
    this.medicine.manufacturer="Abbott";
    this.medicines.push(this.medicine);
    this.medicines.push(this.medicine);
    this.medicines.push(this.medicine);
  }

}
