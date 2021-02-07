import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NumberLiteralType } from 'typescript';
import { Dermatologist } from '../model/dermatologist.model';
import { EmployeeService } from '../service/employee-service';

@Component({
  selector: 'app-appointment-report',
  templateUrl: './appointment-report.component.html',
  styleUrls: ['./appointment-report.component.css']
})
export class AppointmentReportComponent implements OnInit {

  constructor(private route:ActivatedRoute, private service:EmployeeService) { }
  pat:Dermatologist = new Dermatologist;
  userid:number;
  ngOnInit(): void {
    this.userid=Number(this.route.snapshot.paramMap.get('uidn'));
    this.GetPatientForAppointment();
  }
  async GetPatientForAppointment(){
    await this.service.getById(this.userid).then(
      data=>this.pat=data
    )
    console.log(this.pat);
  }
}
