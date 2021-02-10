import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DateRange } from 'src/app/model/date-range.model';
import { ShiftDto } from 'src/app/model/shift-dto.model';
import { ShiftService } from 'src/app/service/shift.service';

@Component({
  selector: 'app-add-shift-pharmacist',
  templateUrl: './add-shift-pharmacist.component.html',
  styleUrls: ['./add-shift-pharmacist.component.css']
})
export class AddShiftPharmacistComponent implements OnInit {

  constructor(private fb: FormBuilder,private shiftService:ShiftService,public dialogRef:MatDialogRef<AddShiftPharmacistComponent>,
    @Inject( MAT_DIALOG_DATA) public data:{
      pharmacistId:number;
      pharmacyId:number;
    }) { 
      this.startDate=new Date();
      this.startDate.setDate(this.startDate.getDate()+1);
      
    }

    shiftDto:ShiftDto=new ShiftDto;
    startDate:Date;
    selectedDateEnd:Date;
    public myForm :FormGroup;
    selectedDate:Date;
    interval:DateRange;
    errorMessage:string;
    shiftAdded:boolean;
    

  ngOnInit(): void {
    this.myForm=this.fb.group({
      date: ['', [Validators.required]],
      startHour:[0,[Validators.required]],
      startMinute:[0,[Validators.required]],
      endHour:[0,[Validators.required]],
      endMinute:[0,[Validators.required]]
    });
    this.errorMessage="";
    this.shiftAdded=true;
    }

  AddShift(){
    this.selectedDate=new Date(this.myForm.controls['date'].value);
    this.selectedDate.setSeconds(0);
    this.selectedDate.setMilliseconds(0);
    this.shiftDto.employeeId=this.data.pharmacistId;
    this.shiftDto.pharmacyId=this.data.pharmacyId;
    this.selectedDate.setHours(this.myForm.controls['startHour'].value+1);
    this.selectedDate.setMinutes(this.myForm.controls['startMinute'].value);
    this.shiftDto.startDateTime=this.selectedDate;
    this.selectedDateEnd=new Date(this.myForm.controls['date'].value);
    this.selectedDateEnd.setSeconds(0);
    this.selectedDateEnd.setMilliseconds(0);
    this.selectedDateEnd.setHours(this.myForm.controls['endHour'].value+1);
    this.selectedDateEnd.setMinutes(this.myForm.controls['endMinute'].value);
    this.shiftDto.endDateTime=this.selectedDateEnd;
    if(this.TimeCheck())
      {
        this.shiftService.AddShift(this.shiftDto).subscribe(
          data=>{
            this.errorMessage="Shift added ",
            this.shiftAdded=false
              },
          err=>this.errorMessage=err.error
        );
      }
    else this.errorMessage="Shift most be at least an hour long";
  }

  TimeCheck():Boolean{
    var sh=this.myForm.controls['startHour'].value*60+this.myForm.controls['startMinute'].value;
    var eh=this.myForm.controls['endHour'].value*60+this.myForm.controls['endMinute'].value;
    console.log(sh);
    console.log(eh);
    return (eh-sh)>=60;
  }
  Return(){
    this.dialogRef.close();
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
}
