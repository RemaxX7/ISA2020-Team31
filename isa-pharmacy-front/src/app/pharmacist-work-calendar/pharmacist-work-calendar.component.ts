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
  async FilterTable(forDays){
    await this.service.getAllCalendarEntries(forDays).then(
      data=>this.entries=data
    )
  }
}
