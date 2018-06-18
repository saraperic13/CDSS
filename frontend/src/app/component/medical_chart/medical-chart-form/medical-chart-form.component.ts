import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MedicalChartService} from "../../../service/medical-chart/medical-chart.service";
import {MedicalChart} from "../../../domain/MedicalChart";
import {FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-medical-chart-form',
  templateUrl: './medical-chart-form.component.html',
  styleUrls: ['./medical-chart-form.component.css']
})
export class MedicalChartFormComponent implements OnInit {

  @Output() chartCreated = new EventEmitter();
  @Output() chartUpdated = new EventEmitter();

  _chartToUpdate: MedicalChart;

  form: FormGroup;

  ssn: FormControl;
  name: FormControl;
  surname: FormControl;

  constructor(private medicalChartService: MedicalChartService) {
  }

  ngOnInit() {
    this.createFormControls();
    this.createForm();
  }

  @Input()
  set chartToUpdate(chart: MedicalChart) {
    this._chartToUpdate = chart;
    if (chart) {
      this.ssn.setValue(chart.ssn);
      this.surname.setValue(chart.surname);
      this.name.setValue(chart.name);
    }
  }

  get chartToUpdate() {
    return this._chartToUpdate;
  }

  createFormControls() {
    this.ssn = new FormControl('', [Validators.required, Validators.minLength(5)]);
    this.name = new FormControl('', [Validators.required, Validators.minLength(1)]);
    this.surname = new FormControl('', [Validators.required, Validators.min(1)]);
  }

  createForm() {
    this.form = new FormGroup({
      ssn: this.ssn,
      surname: this.surname,
      name: this.name
    })
  }

  saveChart(ssn: number, name: string, surname: string) {

    let chart = new MedicalChart();
    chart.name = name;
    chart.surname = surname;
    chart.ssn = ssn;

    if (this._chartToUpdate != null) {
      chart.id = this.chartToUpdate.id;
      this.medicalChartService.update(chart).subscribe(result => {
        this.chartUpdated.emit();
        this.resetFields();
      }, error => {
        // this.invalidFeedback = error.error.message;
        // this.displayMessage = true;
      });
    } else {
      this.medicalChartService.add(chart).subscribe(result => {
        this.chartCreated.emit();
        this.resetFields();
      }, error => {
        // this.invalidFeedback = error.error.message;
        // this.displayMessage = true;
      });
    }
  }

  resetFields() {
    this.form.reset();
  }

}