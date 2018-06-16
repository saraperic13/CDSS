import {Component, OnInit} from '@angular/core';
import {MedicalChart} from "../../../domain/MedicalChart";
import {MedicalChartService} from "../../../service/medical-chart/medical-chart.service";

@Component({
  selector: 'app-medical-chart-page',
  templateUrl: './medical-chart-page.component.html',
  styleUrls: ['./medical-chart-page.component.css']
})
export class MedicalChartPageComponent implements OnInit {

  charts: MedicalChart[];
  chartToUpdate: MedicalChart;

  constructor(private medicalChartService: MedicalChartService) {
  }

  ngOnInit() {
    this.getAll();
  }

  onChartDeleted() {
    this.getAll();
  }

  onChartUpdateClicked(chart: MedicalChart) {
    this.chartToUpdate = chart;
  }

  onChartUpdate() {
    this.getAll();
    this.chartToUpdate = null;
  }

  getAll() {
    this.medicalChartService.getAll().subscribe(res => {
      this.charts = res;
    });
  }
}
