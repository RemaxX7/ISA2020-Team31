import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Dermatologist } from '../model/dermatologist.model';
import { EmployeeService } from '../service/employee-service';

@Component({
  selector: 'app-dermatologist-profile',
  templateUrl: './dermatologist-profile.component.html',
  styleUrls: ['./dermatologist-profile.component.css']
})
export class DermatologistProfileComponent implements OnInit {

  constructor(private fb:FormBuilder,private service:EmployeeService) { }

  dermatologist:Dermatologist = new Dermatologist;
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
    this.dermatologist.email=this.myForm.get('email').value;
    this.dermatologist.name=this.myForm.get('name').value;
    this.dermatologist.surname=this.myForm.get('surname').value;
    this.dermatologist.uidn= this.dermatologist.uidn;
    this.service.editProfile(this.dermatologist).subscribe(()=>alert("Uspesno promenjeni podaci"))
  }
  LoadUser(){
    let user = JSON.parse(localStorage.getItem("user"));
    this.dermatologist.name = user.name;
    this.dermatologist.email = user.email;
    this.dermatologist.surname = user.surname;
    this.dermatologist.uidn = user.uidn;
  }
}
