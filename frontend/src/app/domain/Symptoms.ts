export class Symptoms {
  public symptoms: string[];

  constructor();

  constructor(symptoms: string[]);

  constructor(symptoms?: string[]) {
    this.symptoms = symptoms;
  }
}
