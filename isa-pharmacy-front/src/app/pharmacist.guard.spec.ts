import { TestBed } from "@angular/core/testing";
import { PharmacistGuard } from "./pharmacist.guard";

describe('PharmacistGuard',()=>{
    let guard:PharmacistGuard;
  
    beforeEach(()=>{
      TestBed.configureTestingModule({});
      guard = TestBed.inject(PharmacistGuard);
    });
  
    it('should be created',()=>{
      expect(guard).toBeTruthy();
    });
  });