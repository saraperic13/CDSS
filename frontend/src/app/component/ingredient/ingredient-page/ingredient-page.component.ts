import {Component, OnInit} from '@angular/core';
import {Ingredient} from "../../../domain/Ingredient";
import {IngredientService} from "../../../service/ingredient-service/ingredient.service";

@Component({
  selector: 'app-ingredient-page',
  templateUrl: './ingredient-page.component.html',
  styleUrls: ['./ingredient-page.component.css']
})
export class IngredientPageComponent implements OnInit {

  ingredients: Ingredient[];
  ingredientToUpdate: Ingredient;

  constructor(private ingredientService: IngredientService) {
  }

  ngOnInit() {
    this.getAll();
  }

  onIngredientDeleted() {
    this.getAll();
  }

  onIngredientUpdateClicked(ingredient: Ingredient) {
    this.ingredientToUpdate = ingredient;
  }

  onIngredientUpdate() {
    this.getAll();
    this.ingredientToUpdate = null;
  }

  getAll() {
    this.ingredientService.getAll().subscribe(res => {
      this.ingredients = res;
    });
  }

}
