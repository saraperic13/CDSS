import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiseaseListComponent } from './disease-list.component';

describe('DiseaseListComponent', () => {
  let component: DiseaseListComponent;
  let fixture: ComponentFixture<DiseaseListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiseaseListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiseaseListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
