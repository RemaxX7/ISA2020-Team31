import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeComplaintComponent } from './employee-complaint.component';

describe('EmployeeComplaintComponent', () => {
  let component: EmployeeComplaintComponent;
  let fixture: ComponentFixture<EmployeeComplaintComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeComplaintComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeComplaintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
