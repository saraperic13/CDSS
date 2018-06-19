export class Symptom {

  public name: string;
  public value: string;

  constructor();

  constructor(name: string, value: string);

  constructor(name?: string, value?: string) {
    this.name = name;
    this.value = value;
  }
}
