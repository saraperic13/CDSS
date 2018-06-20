import {Allergy} from "./Allergy";

export class MedicalChart {

  public id: number;
  public ssn: number;
  public name: string;
  public surname: string;
  public diagnosis: number[];
  public allergies: Allergy[];

  constructor();

  constructor(id: number,
              ssn: number,
              name: string,
              surname: string,
              diagnosis: number[],
              allergies:Allergy[]);

  constructor(id?: number,
              ssn?: number,
              name?: string,
              surname?: string,
              diagnosis?: number[],
              allergies?:Allergy[]) {
    this.id = id;
    this.ssn = ssn;
    this.name = name;
    this.surname = surname;
    this.diagnosis = diagnosis;
    this.allergies = allergies;
  }
}
