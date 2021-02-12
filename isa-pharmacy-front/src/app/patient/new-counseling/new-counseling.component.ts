import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { CounselingService } from 'src/app/service/counseling.service';
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
  defaultTime = [0, 0, 0];

  pharmacies: Array<any>;
  pharmacists: Array<any>;


  constructor(private fb: FormBuilder, private pharmacyService: PharmacyService, private counselingService: CounselingService, private toastr: ToastrService) { }

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

  ShowReset() {
    return this.pharmacies.length == 0;
  }

  GetPharmacies() {
    if (this.firstStep.valid) {
      let dateTime = this.firstStep.controls['dateAndTime'].value;
      console.log(dateTime);
      this.pharmacyService.getAvailablePharmacies(dateTime).subscribe(data => {
        this.pharmacies = data;
      },
        err => {
          this.toastr.error(err.error);
        }
      )
    }
  }

  GetPharmacists() {
    if (this.secondStep.valid) {
      let request = {
        dateTime: this.firstStep.controls['dateAndTime'].value,
        pharmacyId: this.secondStep.controls['pharmacy'].value.id
      }
      this.counselingService.getAvailablePharmacists(request).subscribe(data => {
        this.pharmacists = data;
      },
        err => {
          this.toastr.error(err.error);
        }
      )
    }
  }

  ScheduleCounseling() {
    if(this.thirdStep.valid) {
      let counseling = {
        startDateTime: this.firstStep.controls['dateAndTime'].value,
        pharmacyId: this.secondStep.controls['pharmacy'].value.id,
        pharmacistId: this.thirdStep.controls['pharmacist'].value.id
      }
      console.log(counseling);

      this.counselingService.schedule(counseling).subscribe(data => {
        console.log(data);
        this.toastr.success('Counseling appointment scheduled.')
      },
        err => {
          this.toastr.error(err.error);
        }
      )
    }
  }
}