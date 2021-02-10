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
    this.Test();
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
    //this.pharmacist.uidn kad se dovede farmaceut
    this.dermatologist.uidn="3234567891234";
    this.service.editProfile(this.dermatologist).subscribe(()=>alert("Uspesno promenjeni podaci"))
  }
  Test(){
    this.dermatologist.name ="Marko";
    this.dermatologist.email = "mr98@gmail.com";
    this.dermatologist.id = 11111;
    this.dermatologist.surname = "Rakic";
    this.dermatologist.uidn = "3234567891234";
  }
}
