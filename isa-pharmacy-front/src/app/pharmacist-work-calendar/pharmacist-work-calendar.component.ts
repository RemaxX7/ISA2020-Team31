import { Component, OnInit } from '@angular/core';
import { Appointment } from '../model/appointment.model';
import { EmployeeService } from '../service/employee-service';

@Component({
  selector: 'app-pharmacist-work-calendar',
  templateUrl: './pharmacist-work-calendar.component.html',
  styleUrls: ['./pharmacist-work-calendar.component.css']
})
export class PharmacistWorkCalendarComponent implements OnInit {

  constructor(private service:EmployeeService) { }
  entries:Appointment[]=[]
  ngOnInit(): void {
  }
  FillConsultations(days){
    let user = JSON.parse(localStorage.getItem("user"));
    this.service.getConsultationsForPharmacist(user.uidn,days).subscribe(data=>this.entries=data)
  }
}
