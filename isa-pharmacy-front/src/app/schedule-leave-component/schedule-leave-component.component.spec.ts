import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleLeaveComponentComponent } from './schedule-leave-component.component';

describe('ScheduleLeaveComponentComponent', () => {
  let component: ScheduleLeaveComponentComponent;
  let fixture: ComponentFixture<ScheduleLeaveComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleLeaveComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ScheduleLeaveComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
