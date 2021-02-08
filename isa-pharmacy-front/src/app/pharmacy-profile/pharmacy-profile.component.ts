import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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

  constructor(private route: ActivatedRoute,private pharmacyService:PharmacyService,private pharmacistService:PharmacistService,private dermatologistService:DermatologistService) { }
  pharmacy:Pharmacy=new Pharmacy;
  currentRate:number;
  dermatologists:Dermatologist[]=[];
  pharmacists:Pharmacist[]=[];
  medicines:Medicine[]=[];
  pharmacyId:number;
  
  
  lat = 22.2736308;
  long = 70.7512555;
  
  medicine:Medicine=new Medicine;

  ngOnInit(): void {
    this.pharmacyId=Number(this.route.snapshot.paramMap.get('id'));
    this.GetPharmacy();
    this.GetPharmacists();
    this.GetDermatologists();
    this.Test();
   
    
  }

  async GetPharmacy(){
    await this.pharmacyService.getPharmacy(this.pharmacyId).then(data=>{
          this.pharmacy=data;
          this.currentRate = this.pharmacy.rate;
          this.lat=this.pharmacy.address.latitude;
          this.long=this.pharmacy.address.longitude;
        }
      )
  }

 GetPharmacists()
  {
    this.pharmacistService.FindAllByPharmacyId(this.pharmacyId).subscribe(
      data=>{
        this.pharmacists=data;
        
      }
    )
  }

  GetDermatologists()
  {
    this.dermatologistService.FindAllByPharmacyId(this.pharmacyId).subscribe(
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
