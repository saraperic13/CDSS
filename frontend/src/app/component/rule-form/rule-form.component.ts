import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RuleService} from "../../service/rules/rule.service";
import {Rule} from "../../domain/Rule";

@Component({
  selector: 'app-rule-form',
  templateUrl: './rule-form.component.html',
  styleUrls: ['./rule-form.component.css']
})
export class RuleFormComponent implements OnInit {

  form: FormGroup;
  rule: FormControl;

  constructor(private ruleService: RuleService) {
  }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.rule = new FormControl('', [Validators.required]);
    this.form = new FormGroup({
      rule: this.rule
    });

  }

  save() {
    let rule = new Rule();
    rule.definition = this.rule.value;
    this.ruleService.add(rule).subscribe(res => {
      console.log("success");
      this.form.reset();
    });
  }
}
