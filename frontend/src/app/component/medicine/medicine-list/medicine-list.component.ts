import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Medicine} from "../../../domain/Medicine";
import {MedicineService} from "../../../service/medicine-service/medicine.service";

@Component({
  selector: 'app-medicine-list',
  templateUrl: './medicine-list.component.html',
  styleUrls: ['./medicine-list.component.css']
})
export class MedicineListComponent implements OnInit {

  @Input() medicines: Medicine[];

  @Output() medicineDeleted = new EventEmitter();
  @Output() medicineUpdateClicked = new EventEmitter<Medicine>();

  constructor(private medicineService: MedicineService) {
  }

  ngOnInit() {
  }

  deleteMedicine(medicineId: number) {
    this.medicineService.delete(medicineId).subscribe((result) => {
      this.medicineDeleted.emit();
    });
  }

  updateMedicine(medicalMedicine: Medicine) {
    this.medicineUpdateClicked.emit(medicalMedicine);
  }

  getMedicineName(type: number) {
    if (type == 0) {
      return "ANTIBIOTIK";
    } else if (type == 1) {
      return "ANALGETIK"
    } else {
      return "DRUGO";
    }
  }

}
