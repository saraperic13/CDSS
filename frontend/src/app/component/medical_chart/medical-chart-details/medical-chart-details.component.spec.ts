import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicalChartDetailsComponent } from './medical-chart-details.component';

describe('MedicalChartDetailsComponent', () => {
  let component: MedicalChartDetailsComponent;
  let fixture: ComponentFixture<MedicalChartDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedicalChartDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicalChartDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
