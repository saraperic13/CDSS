import { TestBed, inject } from '@angular/core/testing';

import { SymptomService } from './symptom.service';

describe('SymptomService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SymptomService]
    });
  });

  it('should be created', inject([SymptomService], (service: SymptomService) => {
    expect(service).toBeTruthy();
  }));
});
