import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Pharmacist } from '../model/pharmacist.model';
import { EmployeeService } from '../service/employee-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-pharmacist-profile',
  templateUrl: './pharmacist-profile.component.html',
  styleUrls: ['./pharmacist-profile.component.css']
})
export class PharmacistProfileComponent implements OnInit {
  
  constructor(private fb:FormBuilder,private service:EmployeeService,private router:Router,private userService:UserService) { }
  public loaded: boolean = false;
  pharmacist:any;
  myForm:FormGroup;
  ngOnInit(): void {
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
    await this.service.getById(JSON.parse(localStorage.getItem("user")).uidn).then(data=>this.pharmacist = data);
    this.loaded = true;
    this.myForm.controls['name'].setValue(this.pharmacist.name);
    this.myForm.controls['surname'].setValue(this.pharmacist.surname);
    this.myForm.controls['email'].setValue(this.pharmacist.email);
    this.myForm.controls['uidn'].setValue(this.pharmacist.uidn);
  }
  SaveChanges() {
    let profileUpdate = {
      uidn: this.pharmacist.uidn,
      name: this.myForm.controls['name'].value,
      surname: this.myForm.controls['surname'].value,
      email: this.myForm.controls['email'].value
    }

    this.service.editProfile(profileUpdate).subscribe(data => {
      this.Toggle();
      this.pharmacist = profileUpdate;
      alert("Data changed,you will be logged out shortly.");
      this.LogOut();
    },
      err => {
        
      }
    )
  }
  LogOut() {
    localStorage.removeItem('userToken');
    localStorage.removeItem('user');
    this.router.navigate(['login']);

    this.userService.Logout().subscribe(data => {
    },
      err => console.log(err)
    )
  }
}
