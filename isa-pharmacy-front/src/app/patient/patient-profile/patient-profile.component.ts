import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { PatientService } from 'src/app/service/patient.service';
import { City } from 'src/app/shared/Location/city.model';
import { Country } from 'src/app/shared/Location/country.model';
import { LocationService } from 'src/app/shared/Location/location.service';

@Component({
  selector: 'app-patient-profile',
  templateUrl: './patient-profile.component.html',
  styleUrls: ['./patient-profile.component.css']
})
export class PatientProfileComponent implements OnInit {

  constructor(private fb: FormBuilder, private patientService: PatientService, private locationService: LocationService, private toastr: ToastrService) { }

  public loaded: boolean = false;
  profile: any;
  countries: Array<Country>
  cities: Array<City>
  myForm: FormGroup;

  ngOnInit(): void {
    this.FillCountries();
    this.GetProfile();
    this.myForm = this.fb.group({
      name: ['', [Validators.required]],
      surname: ['', [Validators.required]],
      phoneNumber: ['', [Validators.required]],
      city: ['', [Validators.required]],
      country: ['', [Validators.required]],
      street: ['', [Validators.required]],
      number: ['', [Validators.required]],
      latitude: ['', [Validators.required]],
      longitude: ['', [Validators.required]]
    })
    this.myForm.disable();
  }

  GetProfile() {
    this.patientService.getPatientProfile().subscribe(data => {
      this.profile = data;
      this.loaded = true;
      this.FillForm();
    })
  }

  FillCountries() {
    this.locationService.getAllCountries().subscribe(data => {
      this.countries = data;
    })
  }

  FillCities(event: any) {
    this.locationService.getAllCitiesByCountry(event.value.id).subscribe(
      data => {
        this.cities = data;
      }
    )
  }

  GetCoordinates(event: any) {
    this.profile.latitude = event.coords.lat;
    this.profile.longitude = event.coords.lng;
    this.myForm.controls['longitude'].setValue(event.coords.lng);
    this.myForm.controls['latitude'].setValue(event.coords.lat);
  }

  FillForm() {
    this.myForm.controls['street'].setValue(this.profile.street);
    this.myForm.controls['number'].setValue(this.profile.number);
    this.myForm.controls['longitude'].setValue(this.profile.longitude);
    this.myForm.controls['latitude'].setValue(this.profile.latitude);
    this.myForm.controls['name'].setValue(this.profile.name);
    this.myForm.controls['surname'].setValue(this.profile.surname);
    this.myForm.controls['phoneNumber'].setValue(this.profile.phoneNumber);
  }

  Toggle() {
    if (this.myForm.disabled) {
      this.myForm.enable();
      document.getElementById('toggle').innerHTML = 'Cancel';
    } else {
      this.myForm.disable();
      document.getElementById('toggle').innerHTML = 'Edit';
      this.FillForm();
    }
  }

  Save() {
    let profileUpdate = {
      id: this.profile.id,
      name: this.myForm.controls['name'].value,
      surname: this.myForm.controls['surname'].value,
      phoneNumber: this.myForm.controls['phoneNumber'].value,
      street: this.myForm.controls['street'].value,
      number: this.myForm.controls['number'].value,
      latitude: this.myForm.controls['latitude'].value,
      longitude: this.myForm.controls['longitude'].value,
      cityId: this.myForm.controls['city'].value.id,
      countryId: this.myForm.controls['country'].value.id
    }

    this.patientService.updatePatientProfile(profileUpdate).subscribe(data => {
      this.toastr.success('Profile updated');
      this.Toggle();
      this.profile = profileUpdate;
    },
      err => {
        this.toastr.error(err.error);
      }
    )
  }
}
