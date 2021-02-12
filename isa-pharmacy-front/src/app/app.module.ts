import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule} from '@angular/common/http';
import { ReactiveFormsModule ,FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatDividerModule } from '@angular/material/divider';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTabsModule } from '@angular/material/tabs';
import { DermatologistComponent } from './dermatologist/dermatologist.component';
import { DermatologistProfileComponent } from './dermatologist-profile/dermatologist-profile.component';
import { EmployeePasswordChangeComponent } from './employee-password-change/employee-password-change.component';
import { PharmacistComponent } from './pharmacist/pharmacist.component';
import { PharmacistProfileComponent } from './pharmacist-profile/pharmacist-profile.component';
import { PharmacistPasswordChangeComponent } from './pharmacist-password-change/pharmacist-password-change.component';
import { MedicineDispensingComponent } from './medicine-dispensing/medicine-dispensing.component';
import { DermatologistPatientSearchComponent } from './dermatologist-patient-search/dermatologist-patient-search.component';
import { PharmacistPatientSearchComponent } from './pharmacist-patient-search/pharmacist-patient-search.component';
import { PharmacyProfileComponent } from './pharmacy-profile/pharmacy-profile.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PharmacistWorkCalendarComponent } from './pharmacist-work-calendar/pharmacist-work-calendar.component';
import { AppointmentReportComponent } from './appointment-report/appointment-report.component';
import { DermatologistWorkCalendarComponent } from './dermatologist-work-calendar/dermatologist-work-calendar.component';
import { AppointmentReportPharmacistComponent } from './appointment-report-pharmacist/appointment-report-pharmacist.component';
import { PharmacistListComponent } from './pharmacist-list/pharmacist-list.component';
import { DermatologistListComponent } from './dermatologist-list/dermatologist-list.component';
import { AgmCoreModule } from '@agm/core';
import { PharmacyAdminProfileComponent } from './pharmacy-admin-profile/pharmacy-admin-profile.component';
import { PharmacistRegistrationComponent } from './pharmacist-registration/pharmacist-registration.component';
import { AddShiftPharmacistComponent } from './dialog/add-shift-pharmacist/add-shift-pharmacist.component';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { SignUpComponent } from './home/sign-up/sign-up.component';
import { ToastrModule } from 'ngx-toastr';
import { SignInComponent } from './home/sign-in/sign-in.component';
import { HomeComponent } from './home/home.component';
import { PatientComponent } from './patient/patient.component';
import { GuestPharmaciesComponent } from './home/guest-pharmacies/guest-pharmacies.component';
import { GuestMedicinesComponent } from './home/guest-medicines/guest-medicines.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { PatientProfileComponent } from './patient/patient-profile/patient-profile.component';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { NewExamComponent } from './patient/new-exam/new-exam.component';
import { NewCounselingComponent } from './patient/new-counseling/new-counseling.component';
import { UpcomingExamsComponent } from './patient/upcoming-exams/upcoming-exams.component';
import { UpcomingCounselingsComponent } from './patient/upcoming-counselings/upcoming-counselings.component';
import { PastExamsComponent } from './patient/past-exams/past-exams.component';
import { PastCounselingsComponent } from './patient/past-counselings/past-counselings.component';
import { ReservationsComponent } from './patient/reservations/reservations.component';
import { EPrescriptionsComponent } from './patient/eprescriptions/eprescriptions.component';
import { PharmacyComplaintComponent } from './patient/pharmacy-complaint/pharmacy-complaint.component';
import { EmployeeComplaintComponent } from './patient/employee-complaint/employee-complaint.component';
import { DermatologistAppointmentsPageComponent } from './dermatologist-appointments-page/dermatologist-appointments-page.component';
import { PharmacistAppointmentsPageComponent } from './pharmacist-appointments-page/pharmacist-appointments-page.component';
import { PricelistComponent } from './pricelist/pricelist.component';
import { ChangePriceComponent } from './dialog/change-price/change-price.component';

@NgModule({
  declarations: [
    AppComponent,
    DermatologistComponent,
    DermatologistProfileComponent,
    EmployeePasswordChangeComponent,
    PharmacistComponent,
    PharmacistProfileComponent,
    PharmacistPasswordChangeComponent,
    MedicineDispensingComponent,
    DermatologistPatientSearchComponent,
    PharmacistPatientSearchComponent,
    PharmacyProfileComponent,
    PharmacistWorkCalendarComponent,
    AppointmentReportComponent,
    DermatologistWorkCalendarComponent,
    AppointmentReportPharmacistComponent,
    PharmacistListComponent,
    DermatologistListComponent,
    SignUpComponent,
    PharmacyAdminProfileComponent,
    PharmacistRegistrationComponent,
    AddShiftPharmacistComponent,
    PatientComponent,
    SignInComponent,
    HomeComponent,
    GuestPharmaciesComponent,
    GuestMedicinesComponent,
    PatientProfileComponent,
    NewExamComponent,
    NewCounselingComponent,
    UpcomingExamsComponent,
    UpcomingCounselingsComponent,
    PastExamsComponent,
    PastCounselingsComponent,
    ReservationsComponent,
    EPrescriptionsComponent,
    PharmacyComplaintComponent,
    EmployeeComplaintComponent,
    DermatologistAppointmentsPageComponent,
    PharmacistAppointmentsPageComponent,
    PricelistComponent,
    ChangePriceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    HttpClientModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule,
    MatTabsModule,
    MatTableModule,
    MatGridListModule,
    MatDividerModule,
    MatPaginatorModule,
    MatMenuModule,
    MatIconModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBZDx6ISSPAxZfodnJrb5AhMD5omR9CcWg',
      libraries: ['places']
    }),
    NgbModule,
    NgxMaterialTimepickerModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right',
    })
  ],
  providers: [],
  entryComponents: [
    ChangePriceComponent,
    AddShiftPharmacistComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
