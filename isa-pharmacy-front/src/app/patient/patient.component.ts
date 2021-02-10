import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  LogOut() {
    localStorage.removeItem('userToken');
    localStorage.removeItem('user');
    this.router.navigate(['login']);
  }
}
