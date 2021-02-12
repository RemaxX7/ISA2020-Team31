import { Medicine } from "./medicine.model";
import { Patient } from "./patient.model";

export class Appointment {
    patientName:string;
    patientSurname:string;
    duration:number;
    date:string;
    uidn:string;
    report:string;
    status:number;
    medicine:string;
    employeeuidn:string;
    patient: Patient;
    id:any;
	medicineQuantity:string;
}