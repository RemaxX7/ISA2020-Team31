import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientMedicinesComponent } from './patient-medicines.component';

describe('PatientMedicinesComponent', () => {
  let component: PatientMedicinesComponent;
  let fixture: ComponentFixture<PatientMedicinesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientMedicinesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientMedicinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
