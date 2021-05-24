import { Component, OnInit } from '@angular/core';
import { Appointment } from '../model/appointment.model';
import { EmployeeService } from '../service/employee-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-dermatologist-work-calendar',
  templateUrl: './dermatologist-work-calendar.component.html',
  styleUrls: ['./dermatologist-work-calendar.component.css']
})
export class DermatologistWorkCalendarComponent implements OnInit {

  constructor(private service:EmployeeService,private userService:UserService) { }
  entries:Appointment[]=[]

  ngOnInit(): void {
    this.service.refreshJWTToken();
  }

  FillExams(days){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem("user"));
    this.service.getExamsForDermatologist(user.uidn,days).subscribe(data=>this.entries=data);
  }

  CompareValues(a, b) {
    return (a<b) ? -1 : (a>b) ? 1 : 0;
  }
  
  sortTableByDate() {
    let rows = Array.from(document.getElementById("myTable").querySelectorAll('tr'));

    rows = rows.slice(1);
    let qs = `td:nth-child(${3}`;

    rows.sort( (r1,r2) => {
      let t1 = r1.querySelector(qs);
      let t2 = r2.querySelector(qs);

      return this.CompareValues(t1.textContent,t2.textContent);
    });

    rows.forEach(row => document.getElementById("myTable").appendChild(row));
  }

  LogOut() {
    this.userService.Logout().subscribe(data => {
    },
      err => console.log(err)
    )
  }

}
