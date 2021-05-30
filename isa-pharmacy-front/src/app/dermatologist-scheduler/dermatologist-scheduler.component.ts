import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Appointment } from '../model/appointment.model';
import { Dermatologist } from '../model/dermatologist.model';
import { EmployeeService } from '../service/employee-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-dermatologist-scheduler',
  templateUrl: './dermatologist-scheduler.component.html',
  styleUrls: ['./dermatologist-scheduler.component.css']
})
export class DermatologistSchedulerComponent implements OnInit {

  constructor(private service:EmployeeService,private fb:FormBuilder,private userService:UserService,private router:Router) { }

  newDate:string;
  derm:Dermatologist = new Dermatologist();
  freeTermins:any[]=[];
  myForm:FormGroup;
  appointment:Appointment = new Appointment();
  pharmacies:any[]=[];
  pharmID:string;

  ngOnInit(): void {
    
    this.myForm=this.fb.group({
      patientid:['',[Validators.required]]
    })
    this.GetPharmaciesForDermatologist();
  }

  GetFreeTermins(patientid){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem("user"));
    this.derm.uidn = user.uidn;
    this.service.getFreeTermins(patientid,this.derm.uidn).subscribe(data=>this.freeTermins=data,err=>{alert("No user found for given UIDN.Please try again.");window.location.reload()});
  }

  GetPharmaciesForDermatologist(){
    this.service.refreshJWTToken();
    this.service.findpharmaciesfordermatologist().subscribe(data=>this.pharmacies=data,err=>{alert("You are not employed in any pharmacies.")});
  }

  ScheduleExam(patientid){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem("user"));
    this.appointment.uidn=patientid;
    this.appointment.date = this.newDate;
    this.appointment.employeeuidn = user.uidn;
    this.appointment.id = this.pharmID;
    this.service.scheduleNewAppointmentDerm(this.appointment,false).subscribe((res)=>{
      alert("New appointment scheduled.");
      this.router.navigate(['dermatologist']);
    },
      err=>{alert("An error has occured. Please check input data.It's only possible to schedule appointments for patients.")
      window.location.reload();
      }
    );
  }

  LogOut() {
    this.userService.Logout().subscribe(data => {
    },
      err => console.log(err)
    )
  }

}
