import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Medicine} from "../../../domain/Medicine";
import {MedicineService} from "../../../service/medicine-service/medicine.service";

@Component({
  selector: 'app-medicine-form',
  templateUrl: './medicine-form.component.html',
  styleUrls: ['./medicine-form.component.css']
})
export class MedicineFormComponent implements OnInit {

  @Output() medicineCreated = new EventEmitter();
  @Output() medicineUpdated = new EventEmitter();

  _medicineToUpdate: Medicine;

  form: FormGroup;
  name: FormControl;
  selectedType: FormControl;

  @Input()
  set medicineToUpdate(medicine: Medicine) {
    this._medicineToUpdate = medicine;
    if (medicine) {
      this.selectedType.setValue(medicine.type);
      this.name.setValue(medicine.name);
    }
  }

  get medicineToUpdate() {
    return this._medicineToUpdate;
  }

  constructor(private medicineService: MedicineService) {
  }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.name = new FormControl('', [Validators.required]);
    this.selectedType = new FormControl();
    this.form = new FormGroup({
      name: this.name,
      selectedType: this.selectedType
    });

  }

  save() {

    let medicine = new Medicine();
    medicine.name = this.name.value;
    medicine.type = this.selectedType.value;
    ;

    if (this._medicineToUpdate != null) {
      medicine.id = this.medicineToUpdate.id;
      this.medicineService.update(medicine).subscribe(result => {
        this.medicineUpdated.emit();
        this.resetFields();
      }, error => {
        // this.invalidFeedback = error.error.message;
        // this.displayMessage = true;
      });
    } else {
      this.medicineService.add(medicine).subscribe(result => {
        this.medicineCreated.emit();
        this.resetFields();
      }, error => {
        // this.invalidFeedback = error.error.message;
        // this.displayMessage = true;
      });
    }
  }

  resetFields() {
    this.form.reset();
  }


}
