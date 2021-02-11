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
    this.LoadUser();
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
    this.pharmacist.uidn=this.pharmacist.uidn;
    this.service.editProfile(this.pharmacist).subscribe(()=>alert("Uspesno promenjeni podaci"))
  }
  LoadUser(){
    let user = JSON.parse(localStorage.getItem("user"));
    this.pharmacist.name = user.name;
    this.pharmacist.email = user.email;
    this.pharmacist.surname = user.surname;
    this.pharmacist.uidn = user.uidn;
  }
}
