import {Component, Input, OnInit} from '@angular/core';
import {Disease} from "../../domain/Disease";

@Component({
  selector: 'app-disease-card',
  templateUrl: './disease-card.component.html',
  styleUrls: ['./disease-card.component.css']
})
export class DiseaseCardComponent implements OnInit {

  @Input() disease: Disease;

  constructor() { }

  ngOnInit() {
  }

}
