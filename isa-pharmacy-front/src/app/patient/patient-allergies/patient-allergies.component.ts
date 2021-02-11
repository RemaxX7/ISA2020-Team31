import { Component, OnDestroy, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { MedicineService } from 'src/app/service/medicine.service';
import { PatientService } from 'src/app/service/patient.service';

@Component({
  selector: 'app-patient-allergies',
  templateUrl: './patient-allergies.component.html',
  styleUrls: ['./patient-allergies.component.css']
})
export class PatientAllergiesComponent implements OnInit, OnDestroy {

  public medicines: Array<any>;
  public allergies: Array<any>;
  public backupAllergies: Array<any>;
  public totalElements: number;
  private searchActive: boolean = false;
  public loaded = false;
  public changed = false;

  constructor(private medicineService: MedicineService, private patientService: PatientService, private toastr: ToastrService) { }

  ngOnDestroy(): void {
    if (this.changed) {
      this.toastr.info('You left the page without saving changes.')
    }
  }

  ngOnInit(): void {
    this.GetMedicines(0);
    this.GetAllergies();
  }

  GetMedicines(page: number) {
    this.medicineService.getPage(page).subscribe(
      data => {
        this.medicines = data['content'];
        this.totalElements = data['totalElements'];
        this.searchActive = false;
      }
    )
  }

  GetAllergies() {
    this.patientService.getPatientAllergies().subscribe(data => {
      this.allergies = data.allergies;
      this.backupAllergies = JSON.parse(JSON.stringify(data.allergies));
      this.loaded = true;
    })
  }

  Search() {
    let query = (<HTMLInputElement>document.getElementById('search-input')).value;
    if (!query) {
      this.GetMedicines(0);
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
    if (query) {
      this.SearchMedicines(event.pageIndex, query);
    } else {
      this.GetMedicines(event.pageIndex);
    }
  }

  CheckIfEmpty(event: InputEvent) {
    if (!(<HTMLInputElement>document.getElementById('search-input')).value && this.searchActive) {
      this.GetMedicines(0);
    }
  }

  IsMedicineIncluded(medicine) {
    for (let m of this.allergies) {
      if (m.id == medicine.id) {
        return true;
      }
    }
    return false;
  }

  AddAllergy(medicine: any) {
    if (this.IsMedicineIncluded(medicine)) {
      this.toastr.error('Medicine is already on the list.')
    } else {
      this.changed = true;
      this.allergies.push(medicine);
      this.toastr.success('Allergy added.')
    }
  }

  Remove(medicine) {
    this.changed = true;
    for (let m of this.allergies) {
      if (m.id == medicine.id) {
        let index = this.allergies.indexOf(m);
        this.allergies.splice(index, 1);
      }
    }
  }

  Restore() {
    this.allergies = JSON.parse(JSON.stringify(this.backupAllergies));
    this.changed = false;
  }

  Save() {
    let allergies = {
      allergies : this.allergies
    }
    this.patientService.updatePatientAllergies(allergies).subscribe(data => {
      this.toastr.success('Changes saved.')
      this.changed = false;
    },
      err => {
        this.toastr.error(err.error);
      }
    )
  }
}