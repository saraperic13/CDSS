import {Component, Input, OnInit} from '@angular/core';
import {DiagnosticService} from "../../../service/diagnostic-service/diagnostic.service";
import {Diagnosis} from "../../../domain/Diagnosis";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-diagnosis-list',
  templateUrl: './diagnosis-list.component.html',
  styleUrls: ['./diagnosis-list.component.css']
})
export class DiagnosisListComponent implements OnInit {

  @Input() diagnosis: Diagnosis[];
  chartId: number;

  constructor(private diagnosisService: DiagnosticService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.chartId = params['chartId'];
      this.getAll();
    });
  }

  getAll(){
    this.diagnosisService.getAllForChart(this.chartId).subscribe(res=>{
      this.diagnosis = res;
    });
  }

  onDiagnosisDelete(){
    this.getAll();
  }

}
