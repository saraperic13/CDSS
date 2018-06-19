import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Doctor} from "../../../domain/Doctor";
import {DoctorService} from "../../../service/doctor-service/doctor.service";

@Component({
  selector: 'app-doctor-form',
  templateUrl: './doctor-form.component.html',
  styleUrls: ['./doctor-form.component.css']
})
export class DoctorFormComponent implements OnInit {

  @Output() created = new EventEmitter();
  @Output() updated = new EventEmitter();

  _doctorToUpdate: Doctor;

  form: FormGroup;
  name: FormControl;
  surname: FormControl;
  licenceId: FormControl;
  username: FormControl;
  password: FormControl;

  @Input()
  set doctorToUpdate(doctor: Doctor) {
    this._doctorToUpdate = doctor;
    if (doctor) {
      this.name.setValue(doctor.name);
      this.surname.setValue(doctor.surname);
      this.licenceId.setValue(doctor.licenceId);

      this.password.disable();
      this.username.disable();
    }
  }

  get doctorToUpdate() {
    return this._doctorToUpdate;
  }

  constructor(private doctorService: DoctorService) {
  }

  ngOnInit() {
    this.createFormControls();
    this.createForm();
  }

  createFormControls() {
    this.name = new FormControl('', Validators.required);
    this.surname = new FormControl('', Validators.required);
    this.licenceId = new FormControl('', Validators.required);
    this.username = new FormControl('', [Validators.required,
      Validators.minLength(6), Validators.maxLength(40)]);
    this.password = new FormControl('', [Validators.required,
      Validators.minLength(6), Validators.maxLength(40)]);

    this.password.enable();
    this.username.enable();
  }

  createForm() {
    this.form = new FormGroup({
      name: this.name,
      surname: this.surname,
      licenceId: this.licenceId,
      username: this.username,
      password: this.password
    })
  }

  save(name, surname, licenceId, username, password) {
    let doc: Doctor = new Doctor();
    doc.username = username;
    doc.password = password;
    doc.licenceId = licenceId;
    doc.name = name;
    doc.surname = surname;

    if (this._doctorToUpdate != null) {
      doc.id = this.doctorToUpdate.id;
      console.log(doc);
      this.doctorService.update(doc).subscribe(result => {
        this.updated.emit();
        this.password.enable();
        this.username.enable();
        this.resetFields();
      }, error => {
        // this.invalidFeedback = error.error.message;
        // this.displayMessage = true;
      });
    } else {
      this.doctorService.add(doc).subscribe(result => {
        this.created.emit();
        this.resetFields();
      });
    }
  }

  resetFields() {
    this.form.reset();
  }

}
