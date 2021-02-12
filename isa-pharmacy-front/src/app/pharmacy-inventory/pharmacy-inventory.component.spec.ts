import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PharmacyInventoryComponent } from './pharmacy-inventory.component';

describe('PharmacyInventoryComponent', () => {
  let component: PharmacyInventoryComponent;
  let fixture: ComponentFixture<PharmacyInventoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PharmacyInventoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PharmacyInventoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
