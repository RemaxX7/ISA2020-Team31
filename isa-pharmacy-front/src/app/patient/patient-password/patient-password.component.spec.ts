import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientPasswordComponent } from './patient-password.component';

describe('PatientPasswordComponent', () => {
  let component: PatientPasswordComponent;
  let fixture: ComponentFixture<PatientPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientPasswordComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
