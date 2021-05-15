import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Pharmacist } from '../model/pharmacist.model';
import { EmployeeService } from '../service/employee-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-pharmacist-password-change',
  templateUrl: './pharmacist-password-change.component.html',
  styleUrls: ['./pharmacist-password-change.component.css']
})
export class PharmacistPasswordChangeComponent implements OnInit {

  constructor(private fb:FormBuilder,private service:EmployeeService,private router: Router,private userService:UserService) { }
  pharmacist:Pharmacist = new Pharmacist();
  vari:Pharmacist = new Pharmacist();
  myForm:FormGroup;
  ngOnInit(): void {
    this.myForm=this.fb.group({
      pass:['',[Validators.required]],
      passconf:['',[Validators.required]]
    })
  }
  EditPassword(){
    if(this.myForm.get('pass').value != this.myForm.get('passconf').value){
      alert("New passwords do not match!");
    }else{
      let user = JSON.parse(localStorage.getItem("user"));
      console.log(user.name)
      this.pharmacist.uidn = user.uidn;
      this.pharmacist.password = this.myForm.get('pass').value;
      console.log(this.pharmacist.uidn);
      this.service.editPassword(this.pharmacist).subscribe(()=>alert("Password changed"))
      this.LogOut();
    }
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
  async Check(number){
    await this.service.getById(JSON.parse(localStorage.getItem("user")).uidn).then(data=>this.vari=data);
    console.log(this.vari.password)
    //hash je 123
    if(this.vari.password=="$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra"){
      alert("You have to change your password before continuing.Password cannot be '123'");
    }else{
      switch(number){
        case 1:
          this.router.navigate(['pharmacist-patient-search']);
          break;
        case 2:
          this.router.navigate(['pharmacist-appointments']);
          break;
        case 3:
          this.router.navigate(['pharmacist-work-cal']);
          break;
        case 4:
          this.router.navigate(['medicine-dispensing']);
          break;
        case 5:
          this.router.navigate(['pharmacist']);
          break;
        case 6:
          this.router.navigate(['pharmacist']);
          break;
        case 7:
          this.router.navigate(['pharmacist-profile']);
          break;
        case 8:
          this.router.navigate(['pharmacist-profile']);
          break;
        case 9:
          this.router.navigate(['pharmacist-password-change']);
          break;
        case 10:
          this.router.navigate(['pharmacist-work-cal']);
          break;
      }
    }
  }
}
