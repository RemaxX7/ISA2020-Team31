import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PharmacyService } from 'src/app/service/pharmacy.service';

@Component({
  selector: 'app-new-counseling',
  templateUrl: './new-counseling.component.html',
  styleUrls: ['./new-counseling.component.css']
})
export class NewCounselingComponent implements OnInit {

  firstStep: FormGroup;
  secondStep: FormGroup;
  thirdStep: FormGroup;
  isEditable = false;
  min = new Date();

  pharmacies: Array<any>;


  constructor(private fb: FormBuilder, private pharmacyService: PharmacyService) {}

  ngOnInit() {
    this.firstStep = this.fb.group({
      dateAndTime: ['', Validators.required]
    });
    this.secondStep = this.fb.group({
      pharmacy: ['', Validators.required]
    });
    this.thirdStep = this.fb.group({
      pharmacist: ['', Validators.required]
    })
  }

  GetPharmacies() {
    if(this.firstStep.valid) {
      this.pharmacyService.getAll().subscribe(data => {
        this.pharmacies = data; 
      })
    }
  }
}