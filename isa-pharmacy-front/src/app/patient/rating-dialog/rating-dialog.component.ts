import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { ReviewService } from 'src/app/service/review.service';

@Component({
  selector: 'app-rating-dialog',
  templateUrl: './rating-dialog.component.html',
  styleUrls: ['./rating-dialog.component.css']
})
export class RatingDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<RatingDialogComponent>, private reviewService: ReviewService, private toastr: ToastrService,
    @Inject(MAT_DIALOG_DATA) public data: { type: string, target: any }) { }

  public currentRating: number;
  public loaded: boolean = false;
  private response: string;
  public enabled: boolean = false;
  public text: string;

  ngOnInit(): void {
    this.GetRating();
  }

  GetRating() {
    switch (this.data.type) {
      case 'pharmacy':
        this.GetPharmacyRating();
        break;
      case 'medicine':
        this.GetMedicineRating();
        break;
      case 'employee':
        this.GetEmployeeRating();
        break;
    }
  }

  GetPharmacyRating() {
    this.reviewService.getPharmacyRating(this.data.target).subscribe(data => {
      this.currentRating = data;
      if (data == 0) {
        this.response = 'Rating submitted';
        this.text = 'Submit rating';
      } else {
        this.response = 'Rating updated';
        this.text = 'Update rating'
      }
      this.loaded = true;
    })
  }

  GetMedicineRating() {
    this.reviewService.getMedicineRating(this.data.target).subscribe(data => {
      this.currentRating = data;
      if (data == 0) {
        this.response = 'Rating submitted';
        this.text = 'Submit rating';
      } else {
        this.response = 'Rating updated';
        this.text = 'Update rating'
      }
      this.loaded = true;
    })
  }

  GetEmployeeRating() {
    this.reviewService.getEmployeeRating(this.data.target).subscribe(data => {
      this.currentRating = data;
      if (data == 0) {
        this.response = 'Rating submitted';
        this.text = 'Submit rating';
      } else {
        this.response = 'Rating updated';
        this.text = 'Update rating'
      }
      this.loaded = true;
    })
  }

  Rate() {
    switch (this.data.type) {
      case 'pharmacy':
        this.RatePharmacy();
        break;
      case 'medicine':
        this.RateMedicine();
        break;
      case 'employee':
        this.RateEmployee();
        break;
    }
  }

  RatePharmacy() {
    let review = {
      pharmacyId: this.data.target,
      score: this.currentRating
    }

    this.reviewService.ratePharmacy(review).subscribe(data => {
      console.log(data);
      this.toastr.success(this.response);
      this.dialogRef.close();
    },
      err => {
        this.toastr.error(err.error);
      }
    )
  }
  RateMedicine() {
    let review = {
      medicineId: this.data.target,
      score: this.currentRating
    }

    this.reviewService.rateMedicine(review).subscribe(data => {
      this.toastr.success(this.response);
      this.dialogRef.close();
    },
      err => {
        this.toastr.error(err.error);
      }
    )
  }

  RateEmployee() {
    let review = {
      employeeId: this.data.target,
      score: this.currentRating
    }
    console.log(review);
    this.reviewService.rateEmployee(review).subscribe(data => {
      this.toastr.success(this.response);
      this.dialogRef.close();
    },
      err => {
        this.toastr.error(err.error);
      }
    )
  }

  Changed() {
    this.enabled = true;
  }
}
