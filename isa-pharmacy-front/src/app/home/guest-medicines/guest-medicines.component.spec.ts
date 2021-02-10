import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuestMedicinesComponent } from './guest-medicines.component';

describe('GuestMedicinesComponent', () => {
  let component: GuestMedicinesComponent;
  let fixture: ComponentFixture<GuestMedicinesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GuestMedicinesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GuestMedicinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
