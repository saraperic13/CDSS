import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MedicalChartService} from "../../../service/medical-chart/medical-chart.service";
import {MedicalChart} from '../../../domain/MedicalChart';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DiagnosticService} from "../../../service/diagnostic-service/diagnostic.service";
import {Symptom} from "../../../domain/Symptom";
import {Disease} from "../../../domain/Disease";
import {Diagnosis} from "../../../domain/Diagnosis";
import {DiseaseService} from "../../../service/disease-service/disease.service";
import {Medicine} from "../../../domain/Medicine";
import {MedicineService} from "../../../service/medicine-service/medicine.service";

@Component({
  selector: 'app-medical-chart-details',
  templateUrl: './medical-chart-details.component.html',
  styleUrls: ['./medical-chart-details.component.css']
})
export class MedicalChartDetailsComponent implements OnInit {

  chart: MedicalChart;

  calculatedDisease: Disease;
  noDiseaseCalculated: boolean = false;

  diseases: Disease[];

  form: FormGroup;
  symptoms: FormControl;

  diagnosisForm: FormGroup;
  selectedDisease: FormControl;

  checkedMedicines = [];

  medicines: Medicine[];

  diagnosis: Diagnosis = null;

  medicineValidationMessage: string = "";

  constructor(private route: ActivatedRoute,
              private router: Router,
              private medicalChartService: MedicalChartService,
              private diagnosticService: DiagnosticService,
              private diseaseService: DiseaseService,
              private medicineService: MedicineService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      let chartId = params['chartId'];

      this.loadChart(chartId);
    });

    this.createForm();
    this.getAllDiseases();
  }

  createForm() {
    this.symptoms = new FormControl('', [Validators.required]);
    this.form = new FormGroup({
      symptoms: this.symptoms
    });

    this.selectedDisease = new FormControl();
    this.diagnosisForm = new FormGroup({
      selectedDisease: this.selectedDisease
    });
  }

  loadChart(chartId: number) {
    this.medicalChartService.getOne(chartId).subscribe(res => {
      this.chart = res;
    }, error => {
      this.router.navigate(['not_found']);
    });
  }

  diagnose(symptomsString: string) {
    this.calculatedDisease = null;

    let symptomsValues = symptomsString.split(",");
    let symptoms: Symptom[] = [];
    for (let val of symptomsValues) {
      let sympList = val.trim().split(":");
      let symptom = new Symptom(sympList[0], sympList[1]);
      symptoms.push(symptom);
    }
    this.diagnosticService.diagnose(symptoms, this.chart.id).subscribe(res => {
        this.calculatedDisease = res;
        this.noDiseaseCalculated = false;
      },
      error => {
        this.noDiseaseCalculated = true;
      });
  }

  getAllDiseases() {
    this.diseaseService.getAll().subscribe(res => {
      this.diseases = res;
    });

    this.medicineService.getAll().subscribe(res => {
      this.medicines = res;
    });
  }

  setDiagnosis(diseaseId: number) {

    let diagnosis: Diagnosis = new Diagnosis();
    diagnosis.diseaseId = diseaseId;
    diagnosis.symptomsInput = this.symptoms.value;

    this.diagnosticService.setDiagnosis(diagnosis, this.chart.id).subscribe(res => {
        console.log(res);
        this.diagnosis = res;
      },
      error => {
      });
  }

  resetForms() {
    this.form.reset();
    this.diagnosisForm.reset();

    this.checkedMedicines = [];
  }

  getAllergies(allergies) {

    let str = [];
    for (let a of allergies) {
      str.push(a.name);
    }

    return str.join(",");
  }

  validateMedicines() {
    // this.diagnosis.medicines = this.checkedMedicines;
    this.diagnosticService.validateMedicines(this.checkedMedicines, this.chart.id).subscribe(res => {
      console.log(res);
      if (res == true) {
        console.log("SVE OK");
        this.medicineValidationMessage = "Sve je u redu!";
      } else {
        this.medicineValidationMessage = "Pacijent je alergičan na neki od lekova ili njegovih sastojaka!";
      }
    });
  }

  setMedicines() {
    this.diagnosticService.setMedicines(this.checkedMedicines, this.diagnosis.id).subscribe(res => {
      this.resetForms();
      this.diagnosis = null;
    });
  }

  onCheckboxChange(option, event) {
    if (event.target.checked) {
      let ing: Medicine = new Medicine();
      ing.id = option.id;
      this.checkedMedicines.push(ing);
    } else {
      for (var i = 0; i < this.medicines.length; i++) {
        if (this.checkedMedicines[i].id == option.id) {
          this.checkedMedicines.splice(i, 1);
        }
      }
    }
    console.log(this.checkedMedicines);
  }

}
