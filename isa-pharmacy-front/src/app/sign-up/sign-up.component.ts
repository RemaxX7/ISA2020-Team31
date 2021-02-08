import { Component, OnInit } from '@angular/core';
import { User } from '../shared/user.model';
import { FormBuilder,FormGroup , Validators} from '@angular/forms'
import { UserService } from '../shared/user.service';
import { Address } from '../shared/Location/address.model';
import { Country } from '../shared/Location/country.model';
import { City } from '../shared/Location/city.model';
import { LocationService } from '../shared/Location/location.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  myForm :FormGroup;

  latitude = 44.0165;
  longitude = 21.0059;

  constructor(private fb: FormBuilder, private userService:UserService, private locationService:LocationService) { }
 
  User:User=new User();
  Address:Address=new Address();
  countries:Country[]=[];
  cities:City[]=[];
  selectedCity:City=new City();

  ngOnInit(): void {
    this.myForm=this.fb.group({
      name: ['', [Validators.required]],
      surname: ['', [Validators.required]],
      username: ['', [Validators.required]],
      uidn: ['', [Validators.required]],
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
      passwordControl: ['', [Validators.required]],
      phoneNumber: ['', [Validators.required]],
      city: ['', [Validators.required]],
      country: ['',[Validators.required]],
      street: ['', [Validators.required]],
      number: ['', [Validators.required]]
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
        console.log(data);
      }
    )}

    SelectCity(event: any) {
      this.selectedCity = event.value;
      console.log(event.value);
    }
}
