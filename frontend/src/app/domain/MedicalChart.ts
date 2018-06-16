export class MedicalChart {

  public id: number;
  public ssn: number;
  public name: string;
  public surname: string;
  public diagnosis: number[];
  public allergies: number[];

  constructor();

  constructor(id: number,
              ssn: number,
              name: string,
              surname: string,
              diagnosis: number[],
              allergies:number[]);

  constructor(id?: number,
              ssn?: number,
              name?: string,
              surname?: string,
              diagnosis?: number[],
              allergies?:number[]) {
    this.id = id;
    this.ssn = ssn;
    this.name = name;
    this.surname = surname;
    this.diagnosis = diagnosis;
    this.allergies = allergies;
  }
}
