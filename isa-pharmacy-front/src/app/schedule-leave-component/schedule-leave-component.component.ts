import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-schedule-leave-component',
  templateUrl: './schedule-leave-component.component.html',
  styleUrls: ['./schedule-leave-component.component.css']
})
export class ScheduleLeaveComponentComponent implements OnInit {

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
