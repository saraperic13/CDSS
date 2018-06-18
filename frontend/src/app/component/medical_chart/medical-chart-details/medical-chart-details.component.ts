import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MedicalChartService} from "../../../service/medical-chart/medical-chart.service";
import {MedicalChart} from '../../../domain/MedicalChart';
import {Symptoms} from "../../../domain/Symptoms";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DiagnosticService} from "../../../service/diagnostic-service/diagnostic.service";

@Component({
  selector: 'app-medical-chart-details',
  templateUrl: './medical-chart-details.component.html',
  styleUrls: ['./medical-chart-details.component.css']
})
export class MedicalChartDetailsComponent implements OnInit {

  chart: MedicalChart;

  form: FormGroup;

  symptoms: FormControl;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private medicalChartService: MedicalChartService,
              private diagnosticService: DiagnosticService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      let chartId = params['chartId'];

      this.loadChart(chartId);
    });

    this.createForm();
  }

  createForm() {
    this.symptoms = new FormControl('', [Validators.required]);
    this.form = new FormGroup({
      symptoms: this.symptoms
    });
  }

  loadChart(chartId: number) {
    this.medicalChartService.getOne(chartId).subscribe(res => {
      this.chart = res;
    }, error=>{
      this.router.navigate(['not_found']);
    });
  }

  diagnose(symptomsString: string) {
    let symptoms: Symptoms = new Symptoms(symptomsString.split(","));
    this.diagnosticService.diagnose(symptoms).subscribe(res => {
      console.log(res);
    });
  }

}
