import {Component, OnInit} from '@angular/core';
import {Medicine} from "../../../domain/Medicine";
import {MedicineService} from "../../../service/medicine-service/medicine.service";

@Component({
  selector: 'app-medicine-page',
  templateUrl: './medicine-page.component.html',
  styleUrls: ['./medicine-page.component.css']
})
export class MedicinePageComponent implements OnInit {

  medicines: Medicine[];
  medicineToUpdate: Medicine;

  constructor(private medicineService: MedicineService) {
  }

  ngOnInit() {
    this.getAll();
  }

  onMedicineDeleted() {
    this.getAll();
  }

  onMedicineUpdateClicked(medicine: Medicine) {
    this.medicineToUpdate = medicine;
  }

  onMedicineUpdate() {
    this.getAll();
    this.medicineToUpdate = null;
  }

  getAll() {
    this.medicineService.getAll().subscribe(res => {
      this.medicines = res;
    });
  }

}
