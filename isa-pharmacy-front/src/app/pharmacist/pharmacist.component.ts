import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../service/employee-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-pharmacist',
  templateUrl: './pharmacist.component.html',
  styleUrls: ['./pharmacist.component.css']
})
export class PharmacistComponent implements OnInit {

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
