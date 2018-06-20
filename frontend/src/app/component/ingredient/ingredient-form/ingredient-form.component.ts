import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {IngredientService} from "../../../service/ingredient-service/ingredient.service";
import {Ingredient} from "../../../domain/Ingredient";

@Component({
  selector: 'app-ingredient-form',
  templateUrl: './ingredient-form.component.html',
  styleUrls: ['./ingredient-form.component.css']
})
export class IngredientFormComponent implements OnInit {

  @Output() ingredientCreated = new EventEmitter();
  @Output() ingredientUpdated = new EventEmitter();

  _ingredientToUpdate: Ingredient;

  form: FormGroup;
  name: FormControl;

  @Input()
  set ingredientToUpdate(ingredient: Ingredient) {
    this._ingredientToUpdate = ingredient;
    if (ingredient) {
      this.name.setValue(ingredient.name);
    }
  }

  get ingredientToUpdate() {
    return this._ingredientToUpdate;
  }

  constructor(private ingredientService: IngredientService) {
  }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.name = new FormControl('', [Validators.required]);
    this.form = new FormGroup({
      name: this.name
    });
  }

  save() {

    let ingredient = new Ingredient();
    ingredient.name = this.name.value;

    if (this._ingredientToUpdate != null) {
      ingredient.id = this.ingredientToUpdate.id;
      this.ingredientService.update(ingredient).subscribe(result => {
        this.ingredientUpdated.emit();
        this.resetFields();
      }, error => {
        // this.invalidFeedback = error.error.message;
        // this.displayMessage = true;
      });
    } else {
      this.ingredientService.add(ingredient).subscribe(result => {
        this.ingredientCreated.emit();
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
