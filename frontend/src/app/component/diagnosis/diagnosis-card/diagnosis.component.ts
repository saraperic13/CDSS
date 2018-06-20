import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Diagnosis} from "../../../domain/Diagnosis";
import {DiagnosticService} from "../../../service/diagnostic-service/diagnostic.service";

@Component({
  selector: 'app-diagnosis',
  templateUrl: './diagnosis.component.html',
  styleUrls: ['./diagnosis.component.css']
})
export class DiagnosisComponent implements OnInit {

  @Input() diagnosis: Diagnosis;
  @Output() diagnosisDeleteClicked = new EventEmitter();

  constructor(private diagnosisService:DiagnosticService) {
  }

  ngOnInit() {
  }

  onDeleteDiagnosis(){
    this.diagnosisService.delete(this.diagnosis.id).subscribe(res=>{
      this.diagnosisDeleteClicked.emit();
    });
  }
}
