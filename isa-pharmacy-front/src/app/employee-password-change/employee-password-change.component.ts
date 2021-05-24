import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Pharmacist } from '../model/pharmacist.model';
import { EmployeeService } from '../service/employee-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-employee-password-change',
  templateUrl: './employee-password-change.component.html',
  styleUrls: ['./employee-password-change.component.css']
})
export class EmployeePasswordChangeComponent implements OnInit {

  constructor(private fb:FormBuilder,private service:EmployeeService,private router: Router,private userService:UserService) { }
  pharmacist:Pharmacist = new Pharmacist();
  vari:Pharmacist = new Pharmacist();
  myForm:FormGroup;
  ngOnInit(): void {
    this.service.refreshJWTToken();
    this.myForm=this.fb.group({
      pass:['',[Validators.required]],
      passconf:['',[Validators.required]]
    })
  }
  EditPassword(){
    this.service.refreshJWTToken();
    if(this.myForm.get('pass').value != this.myForm.get('passconf').value){
      alert("New passwords do not match!");
    }else if((this.myForm.get('pass').value ==this.myForm.get('passconf').value) && this.myForm.get('pass').value == "123"){
      alert("New password cannot be '123'.");
    }else{
      let user = JSON.parse(localStorage.getItem("user"));
      this.pharmacist.uidn = user.uidn;
      this.pharmacist.password = this.myForm.get('pass').value;
      this.service.editPassword(this.pharmacist).subscribe(()=>alert("Password changed"))
      this.LogOut();
    }
  }
  LogOut() {
    this.service.refreshJWTToken();
    this.userService.Logout().subscribe(data => {
    },
      err => console.log(err)
    )
  }
  async Check(number){
    this.service.refreshJWTToken();
    await this.service.getById(JSON.parse(localStorage.getItem("user")).uidn).then(data=>this.vari=data);
    console.log(this.vari.password)
    //hash je 123
    if(this.vari.password=="$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra"){
      alert("You have to change your password before continuing.Password cannot be '123'");
    }else{
      switch(number){
        case 1:
          this.router.navigate(['dermatologist-patient-search']);
          break;
        case 2:
          this.router.navigate(['dermatologist-appointments']);
          break;
        case 3:
          this.router.navigate(['dermatologist-work-cal']);
          break;
        case 4:
          this.router.navigate(['schedule-leave']);
          break;
        case 5:
          this.router.navigate(['dermatologist']);
          break;
        case 6:
          this.router.navigate(['dermatologist-profile']);
          break;
        case 7:
          this.router.navigate(['dermatologist-profile']);
          break;
        case 8:
          this.router.navigate(['employee-password-change']);
          break;
        case 9:
          this.router.navigate(['dermatologist-work-cal']);
          break;
      }
    }
  }
}
