import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Appointment } from '../model/appointment.model';
import { Dermatologist } from '../model/dermatologist.model';
import { Medicine } from '../model/medicine.model';
import { Patient } from '../model/patient.model';
import { Pharmacist } from '../model/pharmacist.model';
import { EmployeeService } from '../service/employee-service';
import { MedicineService } from '../service/medicine-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-appointment-report-pharmacist',
  templateUrl: './appointment-report-pharmacist.component.html',
  styleUrls: ['./appointment-report-pharmacist.component.css']
})
export class AppointmentReportPharmacistComponent implements OnInit {

  constructor(private fb:FormBuilder, private route:ActivatedRoute, private service:EmployeeService,private medicineService:MedicineService,private router:Router,private userService:UserService) { }
  pat:Patient = new Patient();
  pharm:Pharmacist = new Pharmacist();
  medicine:Medicine[]=[];
  userid:number;
  examid:number;
  appointment:Appointment = new Appointment();
  medicineSpecification:Medicine=new Medicine;
  additionalExam:Appointment = new Appointment();
  medicineSelect:string;
  newDate:string;
  availability:number;
  myForm:FormGroup;
  freeTermins:any[]=[];
  selectedMed:string[]=[];
  medID:number;
  jwt:string;
  clicked = false;
  
  ngOnInit(): void {
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
    alert("Available: " + this.availability +" " + name);
  }
  FinalizeDTOPharmacist(medicine){
    this.service.refreshJWTToken();
    this.appointment.report=this.myForm.get('area').value;
    this.appointment.uidn=this.pat.uidn;
    this.appointment.medicine = medicine
    this.service.sendAppointmentDTOPharmacist(this.appointment,this.examid,this.myForm.get('quantity').value).subscribe(res=>{
      alert("Appointment ended successfully.");
      this.router.navigate(['pharmacist']);
    },
      err =>{
        alert("Not enough medicine in stock.");
        this.selectedMed=[];
      }
     );
  }
  AddMedicine(name,quant){
    this.selectedMed.push(name+","+quant);
  }
  async GetAppointment(){
    this.service.refreshJWTToken();
    await this.service.getByCounselingId(this.examid).then(
      data=>this.appointment=data
    )
  }
  ScheduleAdditionalConsultation(){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem("user"));
    this.additionalExam.uidn=this.pat.uidn;
    this.additionalExam.date = this.newDate;
    this.additionalExam.employeeuidn = user.uidn;
    this.additionalExam.id = this.examid;
    this.service.scheduleNewAppointmentPharm(this.additionalExam).subscribe((res)=>{
      alert("Additional appointment scheduled.");this.clicked=true},err=>{
        alert("Termin not in your work hours.");
      }
    );
  }

  Reload(){
    window.location.reload();
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
    this.pharm.uidn = user.uidn;
    this.service.getFreeTerminsPharm(this.userid,this.pharm.uidn).subscribe(data=>this.freeTermins=data);
  }

  LogOut() {
    this.userService.Logout().subscribe(data => {
    },
      err => console.log(err)
    )
  }
}
