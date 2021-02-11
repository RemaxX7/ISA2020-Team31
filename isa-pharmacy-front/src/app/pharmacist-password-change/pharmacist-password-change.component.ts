import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Pharmacist } from '../model/pharmacist.model';
import { EmployeeService } from '../service/employee-service';

@Component({
  selector: 'app-pharmacist-password-change',
  templateUrl: './pharmacist-password-change.component.html',
  styleUrls: ['./pharmacist-password-change.component.css']
})
export class PharmacistPasswordChangeComponent implements OnInit {

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
      console.log(user.name)
      this.pharmacist.uidn = user.uidn;
      this.pharmacist.password = this.myForm.get('pass').value;
      console.log(this.pharmacist.uidn);
      this.service.editPassword(this.pharmacist).subscribe(()=>alert("Password changed"))
      this.router.navigate(['/pharmacist']);
    }
  }

}
