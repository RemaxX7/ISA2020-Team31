import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpcomingCounselingsComponent } from './upcoming-counselings.component';

describe('UpcomingCounselingsComponent', () => {
  let component: UpcomingCounselingsComponent;
  let fixture: ComponentFixture<UpcomingCounselingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpcomingCounselingsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpcomingCounselingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
