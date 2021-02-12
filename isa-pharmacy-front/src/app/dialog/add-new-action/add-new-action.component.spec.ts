import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewActionComponent } from './add-new-action.component';

describe('AddNewActionComponent', () => {
  let component: AddNewActionComponent;
  let fixture: ComponentFixture<AddNewActionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddNewActionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
