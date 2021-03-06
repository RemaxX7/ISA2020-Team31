import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppointmentReportPharmacistComponent } from './appointment-report-pharmacist/appointment-report-pharmacist.component';
import { AppointmentReportComponent } from './appointment-report/appointment-report.component';
import { DermatologistPatientSearchComponent } from './dermatologist-patient-search/dermatologist-patient-search.component';
import { DermatologistProfileComponent } from './dermatologist-profile/dermatologist-profile.component';
import { DermatologistWorkCalendarComponent } from './dermatologist-work-calendar/dermatologist-work-calendar.component';
import { DermatologistComponent } from './dermatologist/dermatologist.component';
import { EmployeePasswordChangeComponent } from './employee-password-change/employee-password-change.component';
import { MedicineDispensingComponent } from './medicine-dispensing/medicine-dispensing.component';
import { PharmacistPasswordChangeComponent } from './pharmacist-password-change/pharmacist-password-change.component';
import { PharmacistPatientSearchComponent } from './pharmacist-patient-search/pharmacist-patient-search.component';
import { PharmacistProfileComponent } from './pharmacist-profile/pharmacist-profile.component';
import { PharmacistWorkCalendarComponent } from './pharmacist-work-calendar/pharmacist-work-calendar.component';
import { PharmacistComponent } from './pharmacist/pharmacist.component';
import { PharmacyProfileComponent } from './pharmacy-profile/pharmacy-profile.component';
import { PharmacistListComponent } from './pharmacist-list/pharmacist-list.component';
import { DermatologistListComponent } from './dermatologist-list/dermatologist-list.component';
import { SignUpComponent } from './home/sign-up/sign-up.component';
import { HomeComponent } from './home/home.component';
import { SignInComponent } from './home/sign-in/sign-in.component';
import { GuestPharmaciesComponent } from './home/guest-pharmacies/guest-pharmacies.component';
import { GuestMedicinesComponent } from './home/guest-medicines/guest-medicines.component';
import { PatientComponent } from './patient/patient.component';
import { PatientProfileComponent } from './patient/patient-profile/patient-profile.component';
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
import { PatientPharmaciesComponent } from './patient/patient-pharmacies/patient-pharmacies.component';
import { NewReservationComponent } from './patient/new-reservation/new-reservation.component';
import { PatientPasswordComponent } from './patient/patient-password/patient-password.component';
import { PatientAllergiesComponent } from './patient/patient-allergies/patient-allergies.component';
import { PharmacyAdminProfileComponent } from './pharmacy-admin-profile/pharmacy-admin-profile.component';
import { PharmacistRegistrationComponent } from './pharmacist-registration/pharmacist-registration.component';
import { PatientMedicinesComponent } from './patient/patient-medicines/patient-medicines.component';
import { PricelistComponent } from './pricelist/pricelist.component';
import { PharmacyInventoryComponent } from './pharmacy-inventory/pharmacy-inventory.component';
import { PatientGuard } from './patient.guard';
import { PharmacistGuard } from './pharmacist.guard';
import { DermatologistGuard } from './dermatologist.guard';
import { ScheduleLeaveComponentComponent } from './schedule-leave-component/schedule-leave-component.component';
import { ScheduleLeaveComponentPharmacistComponent } from './schedule-leave-component-pharmacist/schedule-leave-component-pharmacist.component';
import { PharmacistSchedulerComponent } from './pharmacist-scheduler/pharmacist-scheduler.component';
import { DermatologistSchedulerComponent } from './dermatologist-scheduler/dermatologist-scheduler.component';

const routes: Routes = [
  { path: 'dermatologist', component: DermatologistComponent,canActivate:[DermatologistGuard] },
  { path: 'dermatologist-profile', component: DermatologistProfileComponent,canActivate:[DermatologistGuard] },
  { path: 'employee-password-change', component: EmployeePasswordChangeComponent,canActivate:[DermatologistGuard] },
  { path: 'pharmacist', component: PharmacistComponent,canActivate:[PharmacistGuard] },
  { path: 'pharmacist-profile', component: PharmacistProfileComponent,canActivate:[PharmacistGuard] },
  { path: 'pharmacist-password-change', component: PharmacistPasswordChangeComponent,canActivate:[PharmacistGuard] },
  { path: 'medicine-dispensing', component: MedicineDispensingComponent,canActivate:[PharmacistGuard] },
  { path: 'dermatologist-patient-search', component: DermatologistPatientSearchComponent,canActivate:[DermatologistGuard] },
  { path: 'pharmacist-patient-search', component: PharmacistPatientSearchComponent,canActivate:[PharmacistGuard] },
  { path: 'pharmacy-profile', component: PharmacyProfileComponent },
  { path: 'pharmacist-work-cal', component: PharmacistWorkCalendarComponent,canActivate:[PharmacistGuard] },
  { path: 'dermatologist-work-cal', component: DermatologistWorkCalendarComponent,canActivate:[DermatologistGuard] },
  { path: 'dermatologist-appointment-report/:uidn', component: AppointmentReportComponent,canActivate:[DermatologistGuard] },
  { path: 'pharmacist-appointment-report/:uidn', component: AppointmentReportPharmacistComponent,canActivate:[PharmacistGuard] },
  { path: 'pharmacy-profile/:id', component: PharmacyProfileComponent },
  { path: 'pharmacist-list', component: PharmacistListComponent },
  { path: 'dermatologist-list', component: DermatologistListComponent,canActivate:[DermatologistGuard] },
  { path: 'dermatologist-appointment-report/:id/:uidn', component: AppointmentReportComponent,canActivate:[DermatologistGuard] },
  { path: 'pharmacist-appointment-report/:id/:uidn', component: AppointmentReportPharmacistComponent,canActivate:[PharmacistGuard] },
  { path: 'dermatologist-appointments', component: DermatologistAppointmentsPageComponent,canActivate:[DermatologistGuard] },
  { path: 'schedule-leave',component:ScheduleLeaveComponentComponent,canActivate:[DermatologistGuard]},
  { path: 'schedule-leave-pharmacist',component:ScheduleLeaveComponentPharmacistComponent,canActivate:[PharmacistGuard]},
  { path: 'pharmacist-appointments', component: PharmacistAppointmentsPageComponent,canActivate:[PharmacistGuard] },
  { path: 'pharmacist-scheduler', component: PharmacistSchedulerComponent,canActivate:[PharmacistGuard] },
  { path: 'dermatologist-scheduler', component: DermatologistSchedulerComponent,canActivate:[DermatologistGuard] },
  {
    path: '', component: HomeComponent,
    children: [{ path: '', component: SignInComponent }]
  },
  { path: 'pharmacy-admin-profile', component: PharmacyAdminProfileComponent },
  { path: 'pharmacist-registration/:id', component: PharmacistRegistrationComponent },
  { path: 'pricelist', component: PricelistComponent },
  {
    path: 'pharmacy-inventory', component: PharmacyInventoryComponent

  },
  {
    path: 'register', component: HomeComponent,
    children: [{ path: '', component: SignUpComponent }]
  },
  {
    path: 'login', component: HomeComponent,
    children: [{ path: '', component: SignInComponent }]
  },
  {
    path: 'pharmacies', component: HomeComponent,
    children: [{ path: '', component: GuestPharmaciesComponent }]
  },
  {
    path: 'medicines', component: HomeComponent,
    children: [{ path: '', component: GuestMedicinesComponent }]
  },
  { path: 'patient', component: PatientComponent, canActivate: [PatientGuard] },
  {
    path: 'patient-profile', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: PatientProfileComponent }]
  },
  {
    path: 'new-exam', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: NewExamComponent }]
  },
  {
    path: 'new-counseling', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: NewCounselingComponent }]
  },
  {
    path: 'upcoming-exams', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: UpcomingExamsComponent }]
  },
  {
    path: 'upcoming-counselings', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: UpcomingCounselingsComponent }]
  },
  {
    path: 'past-exams', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: PastExamsComponent }]
  },
  {
    path: 'past-counselings', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: PastCounselingsComponent }]
  },
  {
    path: 'reservations', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: ReservationsComponent }]
  },
  {
    path: 'eprescriptions', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: EPrescriptionsComponent }]
  },
  {
    path: 'pharmacy-complaint', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: PharmacyComplaintComponent }]
  },
  {
    path: 'employee-complaint', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: EmployeeComplaintComponent }]
  },
  {
    path: 'patient-pharmacies', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: PatientPharmaciesComponent }]
  },
  {
    path: 'new-reservation', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: NewReservationComponent }]
  },
  {
    path: 'patient-password', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: PatientPasswordComponent }]
  },
  {
    path: 'patient-allergies', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: PatientAllergiesComponent }]
  },
  {
    path: 'patient-medicines', component: PatientComponent, canActivate: [PatientGuard],
    children: [{ path: '', component: PatientMedicinesComponent }]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
