import { Component, OnInit } from '@angular/core';
import {ReportService} from "../../service/report/report.service";
import {MedicalChart} from "../../domain/MedicalChart";


@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {

  charts:MedicalChart[];

  constructor(private reportService: ReportService) { }

  ngOnInit() {
  }

  getChronic(){
    this.reportService.getChronic().subscribe(res=>{
      this.charts = res;
    });
  }

  getAddicted(){
    this.reportService.getAddicted().subscribe(res=>{
      this.charts = res;
    });
  }

  getLowImuneSystem(){
    this.reportService.getLowImuneSystem().subscribe(res=>{
      this.charts = res;
    });
  }

}
