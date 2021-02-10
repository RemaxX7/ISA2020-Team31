import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NumberLiteralType } from 'typescript';
import { Appointment } from '../model/appointment.model';
import { Dermatologist } from '../model/dermatologist.model';
import { Medicine } from '../model/medicine.model';
import { Patient } from '../model/patient.model';
import { EmployeeService } from '../service/employee-service';
import { MedicineService } from '../service/medicine-service';

@Component({
  selector: 'app-appointment-report',
  templateUrl: './appointment-report.component.html',
  styleUrls: ['./appointment-report.component.css']
})
export class AppointmentReportComponent implements OnInit {

  constructor(private fb:FormBuilder, private route:ActivatedRoute, private service:EmployeeService,private medicineService:MedicineService) { }
  pat:Patient = new Patient();
  derm:Dermatologist = new Dermatologist();
  medicine:Medicine[]=[];
  userid:number;
  appointment:Appointment = new Appointment();
  additionalExam:Appointment = new Appointment();
  medicineSpecification:Medicine=new Medicine;
  medicineSelect:string;
  newDate:string;
  myForm:FormGroup;
  freeTermins:any[]=[];

  ngOnInit(): void {
    this.userid=Number(this.route.snapshot.paramMap.get('uidn'));
    this.GetPatientForAppointment();
    this.GetAllMedicineForPatient(this.userid);
    this.GetFreeTermins();
    this.myForm=this.fb.group({
      area:['',[Validators.required]]
    })

  }
  FinalizeDTO(){
    this.appointment.report=this.myForm.get('area').value;
    this.appointment.uidn=this.pat.uidn;
    this.appointment.medicine.push(this.medicineSelect);
    this.service.sendAppointmentDTO(this.appointment).subscribe(res=>{
      console.log(res);
      alert("Uspesno zavrsen pregled"),
      err =>{
        alert(err.error);
      }
     } );
  }
  ScheduleAdditionalExam(){
    let user = JSON.parse(localStorage.getItem("user"));
    this.additionalExam.uidn=this.pat.uidn;
    this.additionalExam.date = this.newDate;
    this.additionalExam.employeeuidn = user.uidn;
    this.service.scheduleNewAppointmentDerm(this.additionalExam).subscribe((res)=>
      alert("Uspesno zakazan novi pregled")
    );
  }
  async CallComposition(name){
    await this.medicineService.getCompositionForMedicine(name.toLowerCase()).then(data=>this.medicineSpecification=data)
    alert(this.medicineSpecification.composition);
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
  GetFreeTermins(){
    let user = JSON.parse(localStorage.getItem("user"));
    this.derm.uidn = user.uidn;
    this.service.getFreeTermins(this.userid,this.derm.uidn).subscribe(data=>this.freeTermins=data);
  }
}
