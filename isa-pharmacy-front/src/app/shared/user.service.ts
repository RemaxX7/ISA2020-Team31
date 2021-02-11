import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginCredentials } from './login-credentials.model';
import { User } from './user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  readonly _APIUrl='http://localhost:8080';

  constructor(private _http : HttpClient) { }

  register(user:User) {
    return this._http.post(this._APIUrl + '/auth/register/patient', user);
  }

  Login(credentials:LoginCredentials) {
    return this._http.post(this._APIUrl + '/auth/login', credentials);
  }
}
