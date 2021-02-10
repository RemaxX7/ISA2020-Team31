import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistRegistrationComponent } from './pharmacist-registration.component';

describe('PharmacistRegistrationComponent', () => {
  let component: PharmacistRegistrationComponent;
  let fixture: ComponentFixture<PharmacistRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistRegistrationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
