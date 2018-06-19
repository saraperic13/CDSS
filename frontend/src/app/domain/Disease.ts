import {Symptom} from "./Symptom";

export class Disease {
  public id: number;
  public name: string;
  public type: number;
  public specificSymptoms: Symptom[];
  public commonSymptoms: Symptom[];
}
