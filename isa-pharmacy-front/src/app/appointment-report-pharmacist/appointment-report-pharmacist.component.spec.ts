import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentReportPharmacistComponent } from './appointment-report-pharmacist.component';

describe('AppointmentReportPharmacistComponent', () => {
  let component: AppointmentReportPharmacistComponent;
  let fixture: ComponentFixture<AppointmentReportPharmacistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentReportPharmacistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentReportPharmacistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
