import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Appointment } from '../model/appointment.model';
import { Pharmacist } from '../model/pharmacist.model';
import { EmployeeService } from '../service/employee-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-pharmacist-scheduler',
  templateUrl: './pharmacist-scheduler.component.html',
  styleUrls: ['./pharmacist-scheduler.component.css']
})
export class PharmacistSchedulerComponent implements OnInit {

  constructor(private service:EmployeeService,private fb:FormBuilder,private userService:UserService) { }

  newDate:string;
  pharm:Pharmacist = new Pharmacist();
  freeTermins:any[]=[];
  myForm:FormGroup;
  appointment:Appointment = new Appointment();

  ngOnInit(): void {
    this.myForm=this.fb.group({
      patientid:['',[Validators.required]]
    })
  }

  GetFreeTermins(patientid){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem("user"));
    this.pharm.uidn = user.uidn;
    this.service.getFreeTerminsPharm(patientid,this.pharm.uidn).subscribe(data=>this.freeTermins=data,err=>{alert("No user found for given UIDN.Please try again.");window.location.reload()});
  }

  ScheduleConsultation(patientid){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem("user"));
    this.appointment.uidn=patientid;
    this.appointment.date = this.newDate;
    this.appointment.employeeuidn = user.uidn;
    this.service.scheduleNewAppointmentPharm(this.appointment).subscribe((res)=>{
      alert("New appointment scheduled.");},err=>{
        alert("An error has occured.Please double check your input data.");
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
