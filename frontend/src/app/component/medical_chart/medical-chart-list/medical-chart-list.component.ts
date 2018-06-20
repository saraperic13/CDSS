import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MedicalChartService} from "../../../service/medical-chart/medical-chart.service";
import {MedicalChart} from "../../../domain/MedicalChart";
import {Router} from "@angular/router";

@Component({
  selector: 'app-medical-chart-list',
  templateUrl: './medical-chart-list.component.html',
  styleUrls: ['./medical-chart-list.component.css']
})
export class MedicalChartListComponent implements OnInit {

  @Input() charts: MedicalChart[];
  @Input() modify: boolean;

  @Output() chartDeleted = new EventEmitter();
  @Output() chartUpdateClicked = new EventEmitter<MedicalChart>();

  constructor(private medicalChartService: MedicalChartService,
              private router: Router) { }

  ngOnInit() {
    console.log(this.charts);
  }

  deleteChart(chartId: number) {
    this.medicalChartService.delete(chartId).subscribe((result) => {
      this.chartDeleted.emit();
    });
  }

  updateChart(medicalChart: MedicalChart) {
    this.chartUpdateClicked.emit(medicalChart);
  }

  details(chartId: number) {
    this.router.navigate(['/medical_charts', chartId]);
  }

}
