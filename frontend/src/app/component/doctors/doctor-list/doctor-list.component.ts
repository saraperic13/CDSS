import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Doctor} from "../../../domain/Doctor";
import {DoctorService} from "../../../service/doctor-service/doctor.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DoctorListComponent implements OnInit {

  @Input() doctors: Doctor[];

  @Output() doctorDeleted = new EventEmitter();
  @Output() doctorUpdateClicked = new EventEmitter<Doctor>();

  constructor(private doctorService: DoctorService,
              private router: Router) {
  }

  ngOnInit() {
  }

  delete(chartId: number) {
    this.doctorService.delete(chartId).subscribe((result) => {
      this.doctorDeleted.emit();
    });
  }

  update(doctor: Doctor) {
    this.doctorUpdateClicked.emit(doctor);
  }

}
