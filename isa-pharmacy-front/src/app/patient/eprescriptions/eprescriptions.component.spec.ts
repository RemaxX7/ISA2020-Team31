import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EPrescriptionsComponent } from './eprescriptions.component';

describe('EPrescriptionsComponent', () => {
  let component: EPrescriptionsComponent;
  let fixture: ComponentFixture<EPrescriptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EPrescriptionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EPrescriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
