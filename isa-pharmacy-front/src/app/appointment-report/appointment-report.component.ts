import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { extname } from 'path';
import { isExternalModuleNameRelative, NumberLiteralType } from 'typescript';
import { Appointment } from '../model/appointment.model';
import { Dermatologist } from '../model/dermatologist.model';
import { Medicine } from '../model/medicine.model';
import { Patient } from '../model/patient.model';
import { EmployeeService } from '../service/employee-service';
import { MedicineService } from '../service/medicine-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-appointment-report',
  templateUrl: './appointment-report.component.html',
  styleUrls: ['./appointment-report.component.css']
})
export class AppointmentReportComponent implements OnInit {

  constructor(private fb:FormBuilder, private route:ActivatedRoute, private service:EmployeeService,private medicineService:MedicineService, private router: Router,private userService:UserService) { }
  pat:Patient = new Patient();
  derm:Dermatologist = new Dermatologist();
  medicine:Medicine[]=[];
  userid:number;
  examid:number;
  appointment:Appointment = new Appointment();
  additionalExam:Appointment = new Appointment();
  medicineSpecification:Medicine=new Medicine;
  medicineSelect:string;
  newDate:string;
  availability:number;
  myForm:FormGroup;
  freeTermins:any[]=[];
  selectedMed:string[]=[];
  medID:number;

  ngOnInit(): void {
    this.service.refreshJWTToken();
    this.examid=Number(this.route.snapshot.paramMap.get('id'));
    this.userid=Number(this.route.snapshot.paramMap.get('uidn'));
    
    this.GetAppointment();
    this.GetPatientForAppointment();
    this.GetAllMedicineForPatient(this.userid);
    this.GetFreeTermins();
    this.myForm=this.fb.group({
      area:['',[Validators.required]],
      quantity:['',[Validators.required]]
    })
    

  }
  async MedicineAvailability(name){
    this.service.refreshJWTToken();
    await this.service.medicineAvailability(name.toLowerCase(),this.appointment.id).then(data=>this.availability=data)
    alert("Dostupno je " + this.availability +" " + name);
  }
  FinalizeDTO(medicine){
    this.service.refreshJWTToken();
    console.log(medicine)
    this.appointment.report=this.myForm.get('area').value;
    this.appointment.uidn=this.pat.uidn;
    this.appointment.medicine = medicine;
    this.service.sendAppointmentDTO(this.appointment,this.examid,this.myForm.get('quantity').value).subscribe(res=>{
      console.log(res);
      alert("Uspesno zavrsen pregled");
      this.router.navigate(['dermatologist']);
    },
      err =>{
        alert("Not enough medicine in stock.");
      }
     );
  }
  AddMedicine(name,quant){
    this.selectedMed.push(name+","+quant);
  }
  ScheduleAdditionalExam(){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem("user"));
    this.additionalExam.uidn=this.pat.uidn;
    this.additionalExam.date = this.newDate;
    this.additionalExam.employeeuidn = user.uidn;
    this.additionalExam.id = this.examid;
    this.service.scheduleNewAppointmentDerm(this.additionalExam).subscribe((res)=>
      alert("Uspesno zakazan novi pregled")
    );
  }
  async CallComposition(name){
    this.service.refreshJWTToken();
    await this.medicineService.getCompositionForMedicine(name.toLowerCase()).then(data=>this.medicineSpecification=data)
    alert(this.medicineSpecification.composition);
  }
  async GetPatientForAppointment(){
    this.service.refreshJWTToken();
    await this.service.getById(this.userid).then(
      data=>this.pat=data
    )
    console.log(this.pat);
  }
  async GetAppointment(){
    this.service.refreshJWTToken();
    await this.service.getByExamId(this.examid).then(
      data=>this.appointment=data
    )
    console.log(this.appointment);
  }
  async GetAllMedicineForPatient(userid){
    this.service.refreshJWTToken();
    await this.medicineService.getAllMedicineForPatient(userid).then(
      data=>this.medicine=data
    )
  }
  GetFreeTermins(){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem("user"));
    this.derm.uidn = user.uidn;
    this.service.getFreeTermins(this.userid,this.derm.uidn).subscribe(data=>this.freeTermins=data);
  }
  LogOut() {
    this.userService.Logout().subscribe(data => {
    },
      err => console.log(err)
    )
  }
}
