import { TestBed, inject } from '@angular/core/testing';

import { DiagnosticService } from './diagnostic.service';

describe('DiagnosticService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DiagnosticService]
    });
  });

  it('should be created', inject([DiagnosticService], (service: DiagnosticService) => {
    expect(service).toBeTruthy();
  }));
});
