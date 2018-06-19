import {Component, OnInit} from '@angular/core';
import {Doctor} from "../../../domain/Doctor";
import {DoctorService} from "../../../service/doctor-service/doctor.service";

@Component({
  selector: 'app-doctors-page',
  templateUrl: './doctors-page.component.html',
  styleUrls: ['./doctors-page.component.css']
})
export class DoctorsPageComponent implements OnInit {

  doctors: Doctor[];
  doctorToUpdate: Doctor;

  constructor(private doctorService: DoctorService) {
  }

  ngOnInit() {
    this.getAll();
  }

  onDeleted() {
    this.getAll();
  }

  onUpdateClicked(doctor: Doctor) {
    this.doctorToUpdate = doctor;
  }

  onUpdate() {
    this.getAll();
    this.doctorToUpdate = null;
  }

  getAll() {
    this.doctorService.getAll().subscribe(res => {
      this.doctors = res;
    });
  }

}
