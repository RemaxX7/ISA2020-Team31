import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { PasswordValidator } from 'src/app/home/sign-up/password-validator';
import { PatientService } from 'src/app/service/patient.service';

@Component({
  selector: 'app-patient-password',
  templateUrl: './patient-password.component.html',
  styleUrls: ['./patient-password.component.css']
})
export class PatientPasswordComponent implements OnInit {

  constructor(private fb: FormBuilder, private patientService: PatientService, private toastr: ToastrService) { }

  public myForm: FormGroup;

  ngOnInit(): void {
    this.myForm = this.fb.group({
      password: ['', [Validators.minLength(8), Validators.required]],
      passwordControl: ['', [Validators.minLength(8), Validators.required]]
    }, {
      validator: PasswordValidator.passwordMatchValidator
    })
  }

  ChangePassword() {
    let passwordChange = {
      password: this.myForm.controls['password'].value,
      passwordControl: this.myForm.controls['passwordControl'].value
    }

    this.patientService.updatePassword(passwordChange).subscribe(data => {
      this.toastr.success('Password changed.');
      this.myForm.controls['password'].setValue(null);
      this.myForm.controls['passwordControl'].setValue(null);
    },
      err => {
        this.toastr.error(err.error);
      }
    )
  }
}
