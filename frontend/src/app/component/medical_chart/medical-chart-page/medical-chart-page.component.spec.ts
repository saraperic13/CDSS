import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicalChartPageComponent } from './medical-chart-page.component';

describe('MedicalChartPageComponent', () => {
  let component: MedicalChartPageComponent;
  let fixture: ComponentFixture<MedicalChartPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedicalChartPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicalChartPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
