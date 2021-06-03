import { TestBed } from "@angular/core/testing";
import { DermatologistGuard } from "./dermatologist.guard";

describe('PharmacistGuard',()=>{
    let guard:DermatologistGuard;
  
    beforeEach(()=>{
      TestBed.configureTestingModule({});
      guard = TestBed.inject(DermatologistGuard);
    });
  
    it('should be created',()=>{
      expect(guard).toBeTruthy();
    });
  });