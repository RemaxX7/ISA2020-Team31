import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/user.service';
import {MatDatepickerModule} from '@angular/material/datepicker'
import { EmployeeService } from '../service/employee-service';
import { FormControl, FormGroup } from '@angular/forms';
@Component({
  selector: 'app-schedule-leave-component',
  templateUrl: './schedule-leave-component.component.html',
  styleUrls: ['./schedule-leave-component.component.css']
})
export class ScheduleLeaveComponentComponent implements OnInit {

  constructor(private userService:UserService,private service:EmployeeService) { }
  range= new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });

  ngOnInit(): void {
    this.service.refreshJWTToken();

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
