import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-schedule-leave-component-pharmacist',
  templateUrl: './schedule-leave-component-pharmacist.component.html',
  styleUrls: ['./schedule-leave-component-pharmacist.component.css']
})
export class ScheduleLeaveComponentPharmacistComponent implements OnInit {

  constructor(private userService:UserService) { }

  ngOnInit(): void {
  }
  LogOut() {
    this.userService.Logout().subscribe(data => {
    },
      err => console.log(err)
    )
  }
}
