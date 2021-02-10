import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PastCounselingsComponent } from './past-counselings.component';

describe('PastCounselingsComponent', () => {
  let component: PastCounselingsComponent;
  let fixture: ComponentFixture<PastCounselingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PastCounselingsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PastCounselingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
