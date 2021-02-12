import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { MedicineReservationService } from 'src/app/service/medicine-reservation.service';
import { MedicineService } from 'src/app/service/medicine.service';
import { PharmacyService } from 'src/app/service/pharmacy.service';

@Component({
  selector: 'app-new-reservation',
  templateUrl: './new-reservation.component.html',
  styleUrls: ['./new-reservation.component.css']
})
export class NewReservationComponent implements OnInit {

  public medicines: Array<any>;
  public totalElements: number;
  private searchActive: boolean = false;
  public pharmacies: Array<any>;
  public myForm: FormGroup;
  public min=new Date();

  constructor(private medicineService: MedicineService, private pharmacyService: PharmacyService, private fb: FormBuilder,
    private reservationService: MedicineReservationService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.GetPharmacies();
    this.myForm = this.fb.group({
      pharmacy: ['', [Validators.required]],
      date: ['', [Validators.required]]
    })
  }

  GetPharmacies() {
    this.pharmacyService.getAll().subscribe(data =>
      this.pharmacies = data
    )
  }

  Search() {
    let query = (<HTMLInputElement>document.getElementById('search-input')).value;
    if (!query) {
      this.medicines = null;
      this.totalElements = null;
    } else {
      this.SearchMedicines(0, query);
    }
  }

  SearchMedicines(page: number, query: string) {
    this.medicineService.getSearchResultPage(page, query).subscribe(
      data => {
        this.medicines = data['content'];
        this.totalElements = data['totalElements'];
        this.searchActive = true;
      }
    )
  }

  NextPage(event: PageEvent) {
    let query = (<HTMLInputElement>document.getElementById('search-input')).value;
    this.SearchMedicines(event.pageIndex, query);
  }

  CheckIfEmpty(event: InputEvent) {
    if (!(<HTMLInputElement>document.getElementById('search-input')).value && this.searchActive) {
      this.medicines = null;
      this.totalElements = null;
    }
  }

  MakeReservation(medicine: any) {
    let pharmacy = this.myForm.controls['pharmacy'].value.id;
    let date = this.myForm.controls['date'].value;
    let reservation = {
      pharmacyId: pharmacy,
      pickUpDate: date,
      medicineId: medicine.id
    }    
    
    this.reservationService.makeReservation(reservation).subscribe(data => {
      this.toastr.success('Medicine reservation created.')
    },
    err => {
      this.toastr.error(err.error);
    })
  }
}