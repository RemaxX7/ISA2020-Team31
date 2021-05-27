import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DermatologistSchedulerComponent } from './dermatologist-scheduler.component';

describe('DermatologistSchedulerComponent', () => {
  let component: DermatologistSchedulerComponent;
  let fixture: ComponentFixture<DermatologistSchedulerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DermatologistSchedulerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DermatologistSchedulerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
