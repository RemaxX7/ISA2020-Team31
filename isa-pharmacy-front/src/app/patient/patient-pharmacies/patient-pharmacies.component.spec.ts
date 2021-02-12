import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientPharmaciesComponent } from './patient-pharmacies.component';

describe('PatientPharmaciesComponent', () => {
  let component: PatientPharmaciesComponent;
  let fixture: ComponentFixture<PatientPharmaciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientPharmaciesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientPharmaciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
