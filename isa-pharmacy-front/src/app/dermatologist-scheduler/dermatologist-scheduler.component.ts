import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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

  constructor(private service:EmployeeService,private fb:FormBuilder,private userService:UserService) { }

  newDate:string;
  derm:Dermatologist = new Dermatologist();
  freeTermins:any[]=[];
  myForm:FormGroup;
  appointment:Appointment = new Appointment();

  ngOnInit(): void {
    this.myForm=this.fb.group({
      patientid:['',[Validators.required]],
      pharmacyid:['',[Validators.required]]
    })
  }

  GetFreeTermins(patientid){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem("user"));
    this.derm.uidn = user.uidn;
    this.service.getFreeTermins(patientid,this.derm.uidn).subscribe(data=>this.freeTermins=data,err=>{alert("No user found for given UIDN.Please try again.");window.location.reload()});
  }

  ScheduleExam(patientid,pharmacyid){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem("user"));
    this.appointment.uidn=patientid;
    this.appointment.date = this.newDate;
    this.appointment.employeeuidn = user.uidn;
    this.appointment.id = pharmacyid;
    this.service.scheduleNewAppointmentDerm(this.appointment).subscribe((res)=>{
      alert("Additional appointment scheduled.");},err=>{
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
