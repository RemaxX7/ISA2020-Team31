import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Action } from 'src/app/model/action.model';
import { ActionService } from 'src/app/service/action.service';

@Component({
  selector: 'app-add-new-action',
  templateUrl: './add-new-action.component.html',
  styleUrls: ['./add-new-action.component.css']
})
export class AddNewActionComponent implements OnInit {

  constructor(private actionService:ActionService,private fb: FormBuilder, public dialogRef:MatDialogRef<AddNewActionComponent>,
    @Inject( MAT_DIALOG_DATA) public data:{
      pharmacyId:number;
    }) { }
  public myForm :FormGroup;
  startDate:Date;
  ngOnInit(): void {
    this.myForm=this.fb.group({
      dateBegin: ['', [Validators.required]],
      endBegin: ['', [Validators.required]],
      text:['',[Validators.required]],
      name:['',[Validators.required]]
    });
    this.startDate=new Date();
    this.startDate.setDate(this.startDate.getDate()+1);
  }
  action:Action=new Action;
  addAction(){
    this.action.name=this.myForm.get('name').value;
    this.action.description=this.myForm.get('text').value;
    this.action.startDate=this.myForm.get('dateBegin').value;
    this.action.startDate.setHours(1);
    this.action.endDate=this.myForm.get('endBegin').value;
    this.action.endDate.setHours(23);
    this.action.pharmacyId=this.data.pharmacyId;
    this.actionService.addAction(this.action).subscribe(
      ret=>this.onNoClick()
    );
  }
  onNoClick(): void {
    this.dialogRef.close();
  }

}
