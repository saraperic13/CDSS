import { TestBed, inject } from '@angular/core/testing';

import { MedicalChartService } from './medical-chart.service';

describe('MedicalChartService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MedicalChartService]
    });
  });

  it('should be created', inject([MedicalChartService], (service: MedicalChartService) => {
    expect(service).toBeTruthy();
  }));
});
