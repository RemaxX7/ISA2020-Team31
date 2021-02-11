import { Component, OnInit } from '@angular/core';
import { Appointment } from '../model/appointment.model';
import { EmployeeService } from '../service/employee-service';

@Component({
  selector: 'app-dermatologist-work-calendar',
  templateUrl: './dermatologist-work-calendar.component.html',
  styleUrls: ['./dermatologist-work-calendar.component.css']
})
export class DermatologistWorkCalendarComponent implements OnInit {

  constructor(private service:EmployeeService) { }
  entries:Appointment[]=[]
  ngOnInit(): void {
  }
  FillExams(days){
    let user = JSON.parse(localStorage.getItem("user"));
    this.service.getExamsForDermatologist(user.uidn,days).subscribe(data=>this.entries=data)
  }
}
