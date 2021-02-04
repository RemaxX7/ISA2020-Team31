import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewPharmacistConsultationDialogComponent } from './new-pharmacist-consultation-dialog.component';

describe('NewPharmacistConsultationDialogComponent', () => {
  let component: NewPharmacistConsultationDialogComponent;
  let fixture: ComponentFixture<NewPharmacistConsultationDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewPharmacistConsultationDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewPharmacistConsultationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
