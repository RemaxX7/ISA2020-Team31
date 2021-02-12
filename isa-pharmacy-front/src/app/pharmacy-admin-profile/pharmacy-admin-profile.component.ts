import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-pharmacy-admin-profile',
  templateUrl: './pharmacy-admin-profile.component.html',
  styleUrls: ['./pharmacy-admin-profile.component.css']
})
export class PharmacyAdminProfileComponent implements OnInit {

  constructor(private router: Router) { }

  pharmacyId:number;
  ngOnInit(): void {
    this.pharmacyId=1;
  }


  AddPharmacist(): void {
    this.router.navigate(['/pharmacist-registration', this.pharmacyId]);
    
  }

  LogOut() {
    localStorage.removeItem('userToken');
    localStorage.removeItem('user');
    this.router.navigate(['login']);
  }

}
