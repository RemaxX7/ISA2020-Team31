import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacyComplaintComponent } from './pharmacy-complaint.component';

describe('PharmacyComplaintComponent', () => {
  let component: PharmacyComplaintComponent;
  let fixture: ComponentFixture<PharmacyComplaintComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacyComplaintComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacyComplaintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
