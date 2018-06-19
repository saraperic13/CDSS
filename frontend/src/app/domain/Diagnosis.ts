import {Disease} from "./Disease";
import {Doctor} from "./Doctor";

export class Diagnosis {
  public id: number;
  public doctorName: Doctor;
  public diseaseName: Disease;
  public diseaseId: number;
  public date: string;
  public symptomsInput: string;
  public medicines: string[];
}
