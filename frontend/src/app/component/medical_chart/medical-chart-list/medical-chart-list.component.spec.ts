import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicalChartListComponent } from './medical-chart-list.component';

describe('MedicalChartListComponent', () => {
  let component: MedicalChartListComponent;
  let fixture: ComponentFixture<MedicalChartListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedicalChartListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicalChartListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
