import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { LoginCredentials } from 'src/app/shared/login-credentials.model';
import { UserService } from 'src/app/shared/user.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  public myForm :FormGroup;

  constructor(private fb: FormBuilder, private userService:UserService, private toastr:ToastrService) { }

  ngOnInit(): void {
    this.myForm=this.fb.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]]
    })
  }

  Login() {
    var credentials = new LoginCredentials();
    credentials.username = this.myForm.controls['email'].value;
    credentials.password = this.myForm.controls['password'].value;

    this.userService.Login(credentials).subscribe(data => {
      this.toastr.success('Logged in successfully.');
    },
    err => {
      this.toastr.error(err.error);
    })
  }

}
