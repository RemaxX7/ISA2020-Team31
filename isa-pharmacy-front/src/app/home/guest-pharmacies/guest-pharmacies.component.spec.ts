import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuestPharmaciesComponent } from './guest-pharmacies.component';

describe('GuestPharmaciesComponent', () => {
  let component: GuestPharmaciesComponent;
  let fixture: ComponentFixture<GuestPharmaciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GuestPharmaciesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GuestPharmaciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
