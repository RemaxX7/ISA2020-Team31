import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistAppointmentsPageComponent } from './dermatologist-appointments-page.component';

describe('DermatologistAppointmentsPageComponent', () => {
  let component: DermatologistAppointmentsPageComponent;
  let fixture: ComponentFixture<DermatologistAppointmentsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistAppointmentsPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistAppointmentsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
