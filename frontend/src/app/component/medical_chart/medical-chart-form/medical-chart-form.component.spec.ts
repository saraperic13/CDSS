import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicalChartFormComponent } from './medical-chart-form.component';

describe('MedicalChartFormComponent', () => {
  let component: MedicalChartFormComponent;
  let fixture: ComponentFixture<MedicalChartFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedicalChartFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicalChartFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
