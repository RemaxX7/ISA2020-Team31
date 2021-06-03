import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Dermatologist } from '../model/dermatologist.model';
import { EmployeeService } from '../service/employee-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-dermatologist-profile',
  templateUrl: './dermatologist-profile.component.html',
  styleUrls: ['./dermatologist-profile.component.css']
})
export class DermatologistProfileComponent implements OnInit {

  constructor(private fb:FormBuilder,private service:EmployeeService,private router: Router,private userService:UserService) { }
  public loaded: boolean = false;
  dermatologist:any;
  myForm: FormGroup;
  ngOnInit(): void {
    this.service.refreshJWTToken();
    this.myForm=this.fb.group({
      email:['',[Validators.required]],
      name:['',[Validators.required]],
      surname:['',[Validators.required]],
      uidn:['',[Validators.required]]
      
    })
    this.FillForm();
    this.myForm.disable();
  }
  Toggle(){
    if (this.myForm.disabled) {
      this.myForm.enable();
      document.getElementById('toggle').innerHTML = 'Cancel';
    } else {
      this.myForm.disable();
      document.getElementById('toggle').innerHTML = 'Edit';
      this.FillForm();
    }
  }
  async FillForm() {
    this.service.refreshJWTToken();
    this.loaded = true;
    await this.service.getById(JSON.parse(localStorage.getItem("user")).uidn).then(data=>this.dermatologist = data);
    
    this.myForm.controls['name'].setValue(this.dermatologist.name);
    this.myForm.controls['surname'].setValue(this.dermatologist.surname);
    this.myForm.controls['email'].setValue(this.dermatologist.email);
    this.myForm.controls['uidn'].setValue(this.dermatologist.uidn);
  }

  SaveChanges() {
    this.service.refreshJWTToken();
    let profileUpdate = {
      uidn: this.dermatologist.uidn,
      name: this.myForm.controls['name'].value,
      surname: this.myForm.controls['surname'].value,
      email: this.myForm.controls['email'].value
    }

    this.service.editProfile(profileUpdate).subscribe(data => {
      this.Toggle();
      this.dermatologist = profileUpdate;
      alert("Data changed,you will be logged out shortly.");
      this.LogOut();
    },
      err => {
        
      }
    )
  }
  LogOut() {
    this.userService.Logout().subscribe(data => {
    },
      err => console.log(err)
    )
  }
}
