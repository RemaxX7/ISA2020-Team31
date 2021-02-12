import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddShiftPharmacistComponent } from './add-shift-pharmacist.component';

describe('AddShiftPharmacistComponent', () => {
  let component: AddShiftPharmacistComponent;
  let fixture: ComponentFixture<AddShiftPharmacistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddShiftPharmacistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddShiftPharmacistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
