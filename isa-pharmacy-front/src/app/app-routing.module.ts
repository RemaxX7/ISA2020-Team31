import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DermatologistPatientSearchComponent } from './dermatologist-patient-search/dermatologist-patient-search.component';
import { DermatologistProfileComponent } from './dermatologist-profile/dermatologist-profile.component';
import { DermatologistComponent } from './dermatologist/dermatologist.component';
import { EmployeePasswordChangeComponent } from './employee-password-change/employee-password-change.component';
import { MedicineDispensingComponent } from './medicine-dispensing/medicine-dispensing.component';
import { PharmacistPasswordChangeComponent } from './pharmacist-password-change/pharmacist-password-change.component';
import { PharmacistPatientSearchComponent } from './pharmacist-patient-search/pharmacist-patient-search.component';
import { PharmacistProfileComponent } from './pharmacist-profile/pharmacist-profile.component';
import { PharmacistComponent } from './pharmacist/pharmacist.component';
import { PharmacyProfileComponent } from './pharmacy-profile/pharmacy-profile.component';
import { PharmacistListComponent } from './pharmacist-list/pharmacist-list.component';
import { DermatologistListComponent } from './dermatologist-list/dermatologist-list.component';
import { SignUpComponent } from './patient/sign-up/sign-up.component';
import { HomeComponent } from './home/home.component';
import { SignInComponent } from './patient/sign-in/sign-in.component';

const routes: Routes = [
  {path:'dermatologist',component:DermatologistComponent},
  {path:'dermatologist-profile',component:DermatologistProfileComponent},
  {path:'employee-password-change',component:EmployeePasswordChangeComponent},
  {path:'pharmacist',component:PharmacistComponent},
  {path:'pharmacist-profile',component:PharmacistProfileComponent},
  {path:'pharmacist-password-change',component:PharmacistPasswordChangeComponent},
  {path:'medicine-dispensing',component:MedicineDispensingComponent},
  {path:'dermatologist-patient-search',component:DermatologistPatientSearchComponent},
  {path:'pharmacist-patient-search',component:PharmacistPatientSearchComponent},
  {path: 'pharmacy-profile/:id', component: PharmacyProfileComponent },
  {path:'pharmacist-list', component:PharmacistListComponent},
  {path:'dermatologist-list', component:DermatologistListComponent},
  { path: '', component: HomeComponent },
  {
    path: 'register', component: HomeComponent,
    children: [{ path: '', component: SignUpComponent }]
  },
  {
    path: 'login', component: HomeComponent,
    children: [{ path: '', component: SignInComponent }]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
