import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiseaseFormComponent } from './disease-form.component';

describe('DiseaseFormComponent', () => {
  let component: DiseaseFormComponent;
  let fixture: ComponentFixture<DiseaseFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiseaseFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiseaseFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
