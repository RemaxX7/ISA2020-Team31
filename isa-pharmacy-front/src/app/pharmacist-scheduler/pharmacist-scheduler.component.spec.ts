import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacistSchedulerComponent } from './pharmacist-scheduler.component';

describe('PharmacistSchedulerComponent', () => {
  let component: PharmacistSchedulerComponent;
  let fixture: ComponentFixture<PharmacistSchedulerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacistSchedulerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacistSchedulerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
