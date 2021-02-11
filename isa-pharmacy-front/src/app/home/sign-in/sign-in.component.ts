import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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

  constructor(private fb: FormBuilder, private userService: UserService, private toastr: ToastrService, private router: Router) { }

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

    this.userService.Login(credentials).subscribe((data: any) => {
      this.toastr.success('Logged in successfully.');
      localStorage.setItem('userToken', data.accessToken);
      localStorage.setItem('user', JSON.stringify(data.user));
      this.RedirectUser(data.user.role);
    },
    err => {
      this.toastr.error(err.error);
    })
  }

  RedirectUser(role: string) {
    console.log(this.myForm.controls['password'].value)
    switch(role) {
      case "ROLE_USER": {
        this.router.navigate(['/patient'])
        break;
      }
      case "ROLE_DERMATOLOGIST":{
        if(this.myForm.controls['password'].value == 123){
          this.router.navigate(['/employee-password-change'])
          break;
        }else{
        this.router.navigate(['/dermatologist'])
        break;
        }
      }
      case "ROLE_PHARMACIST":{
        if(this.myForm.controls['password'].value == 123){
          this.router.navigate(['/pharmacist-password-change'])
          break;
        }else{
          this.router.navigate(['/pharmacist'])
          break;
        }
        }
    }
  }

}
