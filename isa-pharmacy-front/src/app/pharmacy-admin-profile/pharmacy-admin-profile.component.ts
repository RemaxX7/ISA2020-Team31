import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-pharmacy-admin-profile',
  templateUrl: './pharmacy-admin-profile.component.html',
  styleUrls: ['./pharmacy-admin-profile.component.css']
})
export class PharmacyAdminProfileComponent implements OnInit {

  constructor(private router: Router) { }

  pharmacyAdminId:number;
  ngOnInit(): void {
    this.pharmacyAdminId=1;
  }

  AddPharmacist(): void {
    this.router.navigate(['/pharmacist-registration', this.pharmacyAdminId]);
    
  }


}
