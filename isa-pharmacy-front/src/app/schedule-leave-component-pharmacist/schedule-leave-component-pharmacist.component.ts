import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { EmployeeService } from '../service/employee-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-schedule-leave-component-pharmacist',
  templateUrl: './schedule-leave-component-pharmacist.component.html',
  styleUrls: ['./schedule-leave-component-pharmacist.component.css']
})
export class ScheduleLeaveComponentPharmacistComponent implements OnInit {

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
