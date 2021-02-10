import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Pharmacist } from '../model/pharmacist.model';
import { EmployeeService } from '../service/employee-service';

@Component({
  selector: 'app-pharmacist-profile',
  templateUrl: './pharmacist-profile.component.html',
  styleUrls: ['./pharmacist-profile.component.css']
})
export class PharmacistProfileComponent implements OnInit {
  
  constructor(private fb:FormBuilder,private service:EmployeeService) { }
  pharmacist:Pharmacist = new Pharmacist();
  myForm:FormGroup;
  ngOnInit(): void {
    this.Test();
    this.myForm=this.fb.group({
      email:['',[Validators.required]],
      name:['',[Validators.required]],
      surname:['',[Validators.required]],
      uidn:['',[Validators.required]]
      
    })
  }
  EditProfile(){
    this.pharmacist.email=this.myForm.get('email').value;
    this.pharmacist.name=this.myForm.get('name').value;
    this.pharmacist.surname=this.myForm.get('surname').value;
    //this.pharmacist.uidn kad se dovede farmaceut
    this.pharmacist.uidn="2234567891234";
    this.service.editProfile(this.pharmacist).subscribe(()=>alert("Uspesno promenjeni podaci"))
  }
  Test(){
    this.pharmacist.name = "Aleksa";
    this.pharmacist.surname = "Rakic"
    this.pharmacist.id = 22222;
    this.pharmacist.email = "ar95@email.com";
    this.pharmacist.uidn = "2234567891234";
  }
}
