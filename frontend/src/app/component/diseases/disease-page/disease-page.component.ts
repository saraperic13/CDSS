import {Component, OnInit} from '@angular/core';
import {Disease} from "../../../domain/Disease";
import {DiseaseService} from "../../../service/disease-service/disease.service";

@Component({
  selector: 'app-disease-page',
  templateUrl: './disease-page.component.html',
  styleUrls: ['./disease-page.component.css']
})
export class DiseasePageComponent implements OnInit {

  diseases: Disease[];
  diseaseToUpdate: Disease;

  constructor(private diseaseService: DiseaseService) {
  }

  ngOnInit() {
    this.getAll();
  }

  onDiseaseDeleted() {
    this.getAll();
  }

  onDiseaseUpdateClicked(disease: Disease) {
    this.diseaseToUpdate = disease;
  }

  onDiseaseUpdate() {
    this.getAll();
    this.diseaseToUpdate = null;
  }

  getAll() {
    this.diseaseService.getAll().subscribe(res => {
      this.diseases = res;
    });
  }

}
