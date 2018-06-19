import { TestBed, inject } from '@angular/core/testing';

import { MedicineService } from './medicine.service';

describe('MedicineService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MedicineService]
    });
  });

  it('should be created', inject([MedicineService], (service: MedicineService) => {
    expect(service).toBeTruthy();
  }));
});
