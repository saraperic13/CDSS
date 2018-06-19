import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicinePageComponent } from './medicine-page.component';

describe('MedicinePageComponent', () => {
  let component: MedicinePageComponent;
  let fixture: ComponentFixture<MedicinePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedicinePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicinePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
