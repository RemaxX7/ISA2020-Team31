import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistPasswordChangeComponent } from './pharmacist-password-change.component';

describe('PharmacistPasswordChangeComponent', () => {
  let component: PharmacistPasswordChangeComponent;
  let fixture: ComponentFixture<PharmacistPasswordChangeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistPasswordChangeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistPasswordChangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
