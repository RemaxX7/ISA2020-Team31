import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Pharmacist } from '../model/pharmacist.model';
import { EmployeeService } from '../service/employee-service';

@Component({
  selector: 'app-employee-password-change',
  templateUrl: './employee-password-change.component.html',
  styleUrls: ['./employee-password-change.component.css']
})
export class EmployeePasswordChangeComponent implements OnInit {

  constructor(private fb:FormBuilder,private service:EmployeeService,private router: Router) { }
  pharmacist:Pharmacist = new Pharmacist();
  myForm:FormGroup;
  ngOnInit(): void {
    this.myForm=this.fb.group({
      pass:['',[Validators.required]],
      passconf:['',[Validators.required]]
    })
  }
  EditPassword(){
    if(this.myForm.get('pass').value != this.myForm.get('passconf').value){
      alert("New passwords do not match!");
    }else{
      let user = JSON.parse(localStorage.getItem("user"));
      this.pharmacist.uidn = user.uidn;
      this.pharmacist.password = this.myForm.get('pass').value;
      this.service.editPassword(this.pharmacist).subscribe(()=>alert("Password changed"))
      this.router.navigate(['/']);
    }
  }
}
