import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistPatientSearchComponent } from './dermatologist-patient-search.component';

describe('DermatologistPatientSearchComponent', () => {
  let component: DermatologistPatientSearchComponent;
  let fixture: ComponentFixture<DermatologistPatientSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistPatientSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistPatientSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
