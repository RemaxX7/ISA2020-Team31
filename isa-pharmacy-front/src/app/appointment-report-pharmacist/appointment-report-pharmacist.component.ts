import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Appointment } from '../model/appointment.model';
import { Dermatologist } from '../model/dermatologist.model';
import { Medicine } from '../model/medicine.model';
import { EmployeeService } from '../service/employee-service';
import { MedicineService } from '../service/medicine-service';

@Component({
  selector: 'app-appointment-report-pharmacist',
  templateUrl: './appointment-report-pharmacist.component.html',
  styleUrls: ['./appointment-report-pharmacist.component.css']
})
export class AppointmentReportPharmacistComponent implements OnInit {

  constructor(private fb:FormBuilder, private route:ActivatedRoute, private service:EmployeeService,private medicineService:MedicineService) { }
  pat:Dermatologist = new Dermatologist();
  medicine:Medicine[]=[];
  userid:number;
  appointment:Appointment = new Appointment();
  medicineSpecification:Medicine=new Medicine;
  additionalExam:Appointment = new Appointment();
  medicineSelect:string;
  newDate:string;
  myForm:FormGroup;
  freeTermins:string[]=[];

  ngOnInit(): void {
    this.userid=Number(this.route.snapshot.paramMap.get('uidn'));
    this.GetPatientForAppointment();
    this.GetAllMedicineForPatient(this.userid);
    this.Test();
    this.myForm=this.fb.group({
      area:['',[Validators.required]],
      //newDate:['',[Validators.required]],
      //medicine:['',[Validators.required]]
    })

  }
  FinalizeDTOPharmacist(){
    this.appointment.report=this.myForm.get('area').value;
    this.appointment.uidn=this.pat.uidn;
    this.appointment.medicine.push(this.medicineSelect);
    this.service.sendAppointmentDTOPharmacist(this.appointment).subscribe(res=>{
      console.log(res);
      alert("Uspesno zavrsen pregled"),
      err =>{
        alert(err.error);
      }
     } );
  }
  ScheduleAdditionalConsultation(){
    this.additionalExam.uidn=this.pat.uidn;
    this.additionalExam.date = this.newDate;
    this.additionalExam.employeeuidn = "2234567891234";
    this.service.scheduleNewAppointmentPharm(this.additionalExam).subscribe((res)=>
      alert("Uspesno zakazan novi pregled")
    );
  }
  Reload(){
    window.location.reload();
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
  
  Test(){
    this.freeTermins.push("2021-10-11 11:30:00");
    this.freeTermins.push("2021-10-12 11:30:00");
    this.freeTermins.push("2021-10-13 11:30:00");
    this.freeTermins.push("2021-10-14 11:30:00");
  }
}
