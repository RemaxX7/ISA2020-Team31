import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewCounselingComponent } from './new-counseling.component';

describe('NewCounselingComponent', () => {
  let component: NewCounselingComponent;
  let fixture: ComponentFixture<NewCounselingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewCounselingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewCounselingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
