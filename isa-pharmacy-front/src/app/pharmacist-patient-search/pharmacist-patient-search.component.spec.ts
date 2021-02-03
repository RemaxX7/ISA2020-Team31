import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistPatientSearchComponent } from './pharmacist-patient-search.component';

describe('PharmacistPatientSearchComponent', () => {
  let component: PharmacistPatientSearchComponent;
  let fixture: ComponentFixture<PharmacistPatientSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistPatientSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistPatientSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
