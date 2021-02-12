import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { AddShiftPharmacistComponent } from '../dialog/add-shift/add-shift-pharmacist.component';
import { Pharmacist } from '../model/pharmacist.model';
import { PharmacistService } from '../service/pharmacist.service';
import { Address } from '../shared/Location/address.model';
import { City } from '../shared/Location/city.model';
import { Country } from '../shared/Location/country.model';
import { LocationService } from '../shared/Location/location.service';
import { User } from '../shared/user.model';
import { PasswordValidator } from '../home/sign-up/password-validator';

@Component({
  selector: 'app-pharmacist-registration',
  templateUrl: './pharmacist-registration.component.html',
  styleUrls: ['./pharmacist-registration.component.css']
})
export class PharmacistRegistrationComponent implements OnInit {

  public myForm :FormGroup;
 

  latitude = 44.0165;
  longitude = 21.0059;
  locationChosen = false;

  constructor(private fb: FormBuilder,public dialog: MatDialog, private route: ActivatedRoute,private router: Router,private pharmacistService:PharmacistService, private locationService:LocationService) { }

  pharmacist:Pharmacist=new Pharmacist;
  countries:Country[]=[];
  cities:City[]=[];
  selectedCity:City=new City();

  pharmacyId:number;
  shifts:boolean;

  ngOnInit(): void {
    this.pharmacyId=Number(this.route.snapshot.paramMap.get('id'));
    this.myForm=this.fb.group({
      name: ['', [Validators.required]],
      surname: ['', [Validators.required]],
      username: ['', [Validators.required]],
      uidn: ['', [Validators.required]],
      email: [null, Validators.compose([
        Validators.email,
        Validators.required])
     ],
      password: ['', [Validators.minLength(8)]],
      passwordControl: ['', [Validators.minLength(8)]],
      phoneNumber: ['', [Validators.required]],
      city: ['', [Validators.required]],
      country: ['',[Validators.required]],
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
        console.log(data);
      }
    )}

    SelectCity(event: any) {
      this.selectedCity = event.value;
      console.log(event.value);
    }

    GetCoordinates(event: any) {
      this.latitude = event.coords.lat;
      this.longitude = event.coords.lng;
      this.locationChosen = true;
      this.myForm.controls['longitude'].setValue(event.coords.lng);
      this.myForm.controls['latitude'].setValue(event.coords.lat);
    }

/*
    AddShifts(){
      const dialogRef = this.dialog.open(AddShiftPharmacistComponent,{
        height:"500px",
        data:{
          pharmacistId:this.pharmacist.id,
          pharmacyId:this.pharmacist.pharmacy.id
        }
      });
  
      dialogRef.afterClosed().subscribe(result => {
       this.ngOnInit();
       this.router.navigate(['/pharmacist-list']);
      });
    }*/

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
      
      this.pharmacistService.Register(user,this.pharmacyId).subscribe(
        data=>{
          this.pharmacist=data;
          alert("Registration successful"),
          this.router.navigate(['/pharmacist-list']);
        }

      );
    }

}
