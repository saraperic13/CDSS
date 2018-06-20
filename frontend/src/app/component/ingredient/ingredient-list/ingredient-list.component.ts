import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {IngredientService} from "../../../service/ingredient-service/ingredient.service";
import {Ingredient} from "../../../domain/Ingredient";

@Component({
  selector: 'app-ingredient-list',
  templateUrl: './ingredient-list.component.html',
  styleUrls: ['./ingredient-list.component.css']
})
export class IngredientListComponent implements OnInit {

  @Input() ingredients: Ingredient[];

  @Output() ingredientDeleted = new EventEmitter();
  @Output() ingredientUpdateClicked = new EventEmitter<Ingredient>();

  constructor(private ingredientService: IngredientService) {
  }

  ngOnInit() {
  }

  deleteIngredient(ingredientId: number) {
    this.ingredientService.delete(ingredientId).subscribe((result) => {
      this.ingredientDeleted.emit();
    });
  }

  updateIngredient(ingredient: Ingredient) {
    this.ingredientUpdateClicked.emit(ingredient);
  }

}
