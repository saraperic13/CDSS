export class Symptom {

  public id: number;

  public name: string;
  public value: string;

  constructor();

  constructor(name: string, value: string, id: number);

  constructor(name?: string, value?: string, id?: number) {
    this.name = name;
    this.value = value;
    this.id = id;
  }
}
