import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Disease} from "../../../domain/Disease";
import {DiseaseService} from "../../../service/disease-service/disease.service";

@Component({
  selector: 'app-disease-list',
  templateUrl: './disease-list.component.html',
  styleUrls: ['./disease-list.component.css']
})
export class DiseaseListComponent implements OnInit {


  @Input() diseases: Disease[];

  @Output() diseaseDeleted = new EventEmitter();
  @Output() diseaseUpdateClicked = new EventEmitter<Disease>();

  constructor(private diseaseService: DiseaseService) {
  }

  ngOnInit() {
  }

  deleteDisease(diseaseId: number) {
    this.diseaseService.delete(diseaseId).subscribe((result) => {
      this.diseaseDeleted.emit();
    });
  }

  updateDisease(medicalDisease: Disease) {
    this.diseaseUpdateClicked.emit(medicalDisease);
  }

  getDiseaseName(type: number) {
    if (type == 0) {
      return "Prva";
    } else if (type == 1) {
      return "Druga"
    } else {
      return "Treća";
    }
  }
}
