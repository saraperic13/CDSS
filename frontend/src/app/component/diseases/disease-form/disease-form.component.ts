import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Disease} from "../../../domain/Disease";
import {Symptom} from "../../../domain/Symptom";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DiseaseService} from "../../../service/disease-service/disease.service";
import {SymptomService} from "../../../service/symptom/symptom.service";

@Component({
  selector: 'app-disease-form',
  templateUrl: './disease-form.component.html',
  styleUrls: ['./disease-form.component.css']
})
export class DiseaseFormComponent implements OnInit {

  @Output() diseaseCreated = new EventEmitter();
  @Output() diseaseUpdated = new EventEmitter();

  _diseaseToUpdate: Disease;

  symptoms: Symptom[];

  checkedCommonSymptoms = [];
  checkedSpecificSymptoms = [];

  form: FormGroup;
  name: FormControl;
  selectedType: FormControl;

  @Input()
  set diseaseToUpdate(disease: Disease) {
    this._diseaseToUpdate = disease;
    if (disease) {
      this.selectedType.setValue(disease.type);
      this.name.setValue(disease.name);
    }
  }

  get diseaseToUpdate() {
    return this._diseaseToUpdate;
  }

  constructor(private diseaseService: DiseaseService,
              private symptomService: SymptomService) {
  }

  ngOnInit() {
    this.createForm();
    this.getSymptoms();
  }

  createForm() {
    this.name = new FormControl('', [Validators.required]);
    this.selectedType = new FormControl();
    this.form = new FormGroup({
      name: this.name,
      selectedType: this.selectedType
    });

  }

  getSymptoms() {
    this.symptomService.getAll().subscribe(res => {
      this.symptoms = res;
    });
  }

  save() {

    let disease = new Disease();
    disease.name = this.name.value;
    disease.type = this.selectedType.value;
    disease.commonSymptoms = this.checkedCommonSymptoms;
    disease.specificSymptoms = this.checkedSpecificSymptoms;


    if (this._diseaseToUpdate != null) {
      disease.id = this.diseaseToUpdate.id;
      this.diseaseService.update(disease).subscribe(result => {
        this.diseaseUpdated.emit();
        this.resetFields();
        console.log("EVO USEPLA");
      }, error => {
        // this.invalidFeedback = error.error.message;
        // this.displayMessage = true;
      });
    } else {
      this.diseaseService.add(disease).subscribe(result => {
        this.diseaseCreated.emit();
        this.resetFields();
      }, error => {
        // this.invalidFeedback = error.error.message;
        // this.displayMessage = true;
      });
    }
  }

  resetFields() {
    this.form.reset();
    let aa = document.getElementsByClassName("box");
    for (var i = 0, n = aa.length; i < n; i++) {
      // aa[i].checked = false;
    }
    this.checkedCommonSymptoms = [];
    this.checkedSpecificSymptoms = [];
  }

  onCommonChange(option, event) {
    if (event.target.checked) {
      let ing: Symptom = new Symptom();
      ing.id = option.id;
      this.checkedCommonSymptoms.push(ing);
    } else {
      for (var i = 0; i < this.symptoms.length; i++) {
        if (this.checkedCommonSymptoms[i].id == option.id) {
          this.checkedCommonSymptoms.splice(i, 1);
        }
      }
    }
    console.log(this.checkedCommonSymptoms);
  }

  onSpecificChange(option, event) {
    if (event.target.checked) {
      let ing: Symptom = new Symptom();
      ing.id = option.id;
      this.checkedSpecificSymptoms.push(ing);
    } else {
      for (var i = 0; i < this.symptoms.length; i++) {
        if (this.checkedSpecificSymptoms[i].id == option.id) {
          this.checkedSpecificSymptoms.splice(i, 1);
        }
      }
    }
    console.log(this.checkedSpecificSymptoms);
  }

}
