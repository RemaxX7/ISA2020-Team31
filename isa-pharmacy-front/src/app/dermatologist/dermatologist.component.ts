import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../service/employee-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-dermatologist',
  templateUrl: './dermatologist.component.html',
  styleUrls: ['./dermatologist.component.css']
})
export class DermatologistComponent implements OnInit {

  constructor(private service:EmployeeService,private userService:UserService) { }

  ngOnInit(): void {
    this.service.refreshJWTToken();
  }
  LogOut() {
    this.userService.Logout().subscribe(data => {
    },
      err => console.log(err)
    )
  }
}
