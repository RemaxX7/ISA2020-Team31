import { Component, OnInit } from '@angular/core';
import { User } from '../../shared/user.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { UserService } from '../../shared/user.service';
import { Address } from '../../shared/Location/address.model';
import { Country } from '../../shared/Location/country.model';
import { City } from '../../shared/Location/city.model';
import { LocationService } from '../../shared/Location/location.service';
import { PasswordValidator } from './password-validator';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  public myForm: FormGroup;

  latitude = 44.0165;
  longitude = 21.0059;
  locationChosen = false;

  constructor(private fb: FormBuilder, private userService: UserService, private locationService: LocationService, private toastr: ToastrService) { }

  countries: Country[] = [];
  cities: City[] = [];

  ngOnInit(): void {
    this.myForm = this.fb.group({
      name: ['', [Validators.required]],
      surname: ['', [Validators.required]],
      username: ['', [Validators.required]],
      uidn: ['', [Validators.required]],
      email: [null, Validators.compose([
        Validators.email,
        Validators.required])
      ],
      password: ['', [Validators.minLength(8), Validators.required]],
      passwordControl: ['', [Validators.minLength(8), Validators.required]],
      phoneNumber: ['', [Validators.required]],
      city: ['', [Validators.required]],
      country: ['', [Validators.required]],
      street: ['', [Validators.required]],
      number: ['', [Validators.required]],
      latitude: ['', [Validators.required]],
      longitude: ['', [Validators.required]]
    }, {
      validator: PasswordValidator.passwordMatchValidator
    })
    this.FillCountries();
  }

  FillCountries() {
    this.locationService.getAllCountries().subscribe(
      data => this.countries = data
    );
  }

  FillCities(event: any) {
    this.locationService.getAllCitiesByCountry(event.value.id).subscribe(
      data => {
        this.cities = data;
      }
    )
  }

  GetCoordinates(event: any) {
    this.latitude = event.coords.lat;
    this.longitude = event.coords.lng;
    this.locationChosen = true;
    this.myForm.controls['longitude'].setValue(event.coords.lng);
    this.myForm.controls['latitude'].setValue(event.coords.lat);
  }

  Register() {
    var address = new Address();
    address.cityId = this.myForm.controls['city'].value.id;
    address.street = this.myForm.controls['street'].value;
    address.number = this.myForm.controls['number'].value;
    address.longitude = this.myForm.controls['longitude'].value;
    address.latitude = this.myForm.controls['latitude'].value;

    var user = new User();
    user.name = this.myForm.controls['name'].value;
    user.surname = this.myForm.controls['surname'].value;
    user.username = this.myForm.controls['username'].value;
    user.uidn = this.myForm.controls['uidn'].value;
    user.email = this.myForm.controls['email'].value;
    user.password = this.myForm.controls['password'].value;
    user.phoneNumber = this.myForm.controls['phoneNumber'].value;
    user.address = address;

    this.userService.register(user).subscribe(data => {
      this.toastr.success('Registered successfully. Activation email has been sent.');
    },
      err => {
        this.toastr.error(err.error);
      }
    );
  }
}
