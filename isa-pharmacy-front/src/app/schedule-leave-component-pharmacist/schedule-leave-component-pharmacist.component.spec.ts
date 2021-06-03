import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleLeaveComponentPharmacistComponent } from './schedule-leave-component-pharmacist.component';

describe('ScheduleLeaveComponentPharmacistComponent', () => {
  let component: ScheduleLeaveComponentPharmacistComponent;
  let fixture: ComponentFixture<ScheduleLeaveComponentPharmacistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleLeaveComponentPharmacistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ScheduleLeaveComponentPharmacistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
