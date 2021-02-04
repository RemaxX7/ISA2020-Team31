import { Inject } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-new-pharmacist-consultation-dialog',
  templateUrl: './new-pharmacist-consultation-dialog.component.html',
  styleUrls: ['./new-pharmacist-consultation-dialog.component.css']
})
export class NewPharmacistConsultationDialogComponent implements OnInit {

  constructor(private fb: FormBuilder,public dialogRef:MatDialogRef<NewPharmacistConsultationDialogComponent>,
    @Inject( MAT_DIALOG_DATA) public data:{
      pharmacistId:number
    }) { }

  ngOnInit(): void {
    console.log(this.data.pharmacistId);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
