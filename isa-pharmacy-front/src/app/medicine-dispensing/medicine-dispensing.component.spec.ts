import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicineDispensingComponent } from './medicine-dispensing.component';

describe('MedicineDispensingComponent', () => {
  let component: MedicineDispensingComponent;
  let fixture: ComponentFixture<MedicineDispensingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicineDispensingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicineDispensingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
