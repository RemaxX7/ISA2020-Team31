import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistAppointmentsPageComponent } from './pharmacist-appointments-page.component';

describe('PharmacistAppointmentsPageComponent', () => {
  let component: PharmacistAppointmentsPageComponent;
  let fixture: ComponentFixture<PharmacistAppointmentsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistAppointmentsPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistAppointmentsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
