import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/user.service';
import {MatDatepickerModule} from '@angular/material/datepicker'
import { EmployeeService } from '../service/employee-service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-schedule-leave-component',
  templateUrl: './schedule-leave-component.component.html',
  styleUrls: ['./schedule-leave-component.component.css']
})
export class ScheduleLeaveComponentComponent implements OnInit {

  constructor(private userService:UserService,private service:EmployeeService,private fb:FormBuilder) { }
  range= new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });

  ngOnInit(): void {
    this.service.refreshJWTToken();
    this.range=this.fb.group({
      start:['',[Validators.required]],
      end:['',[Validators.required]]
    })

  }

  PlaceRequest(startDate){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem('user'));
    this.service.requestLeave(user.uidn,startDate).subscribe(()=> alert("Your request has been sent.You will get a response soon."));
  }

  LogOut() {
    this.userService.Logout().subscribe(data => {
    },
      err => console.log(err)
    )
  }
}
