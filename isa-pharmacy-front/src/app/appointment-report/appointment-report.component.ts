import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NumberLiteralType } from 'typescript';
import { Appointment } from '../model/appointment.model';
import { Dermatologist } from '../model/dermatologist.model';
import { Medicine } from '../model/medicine.model';
import { EmployeeService } from '../service/employee-service';
import { MedicineService } from '../service/medicine-service';

@Component({
  selector: 'app-appointment-report',
  templateUrl: './appointment-report.component.html',
  styleUrls: ['./appointment-report.component.css']
})
export class AppointmentReportComponent implements OnInit {

  constructor(private route:ActivatedRoute, private service:EmployeeService,private medicineService:MedicineService) { }
  pat:Dermatologist = new Dermatologist;
  medicine:Medicine[]=[];
  userid:number;
  ngOnInit(): void {
    this.userid=Number(this.route.snapshot.paramMap.get('uidn'));
    this.GetPatientForAppointment();
    this.GetAllMedicineForPatient(this.userid);
  }
  async GetPatientForAppointment(){
    await this.service.getById(this.userid).then(
      data=>this.pat=data
    )
    console.log(this.pat);
  }
  async GetAllMedicineForPatient(userid){
    await this.medicineService.getAllMedicineForPatient(userid).then(
      data=>this.medicine=data
    )
  }
}
