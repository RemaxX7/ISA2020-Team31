import { Medicine } from "./medicine.model";
import { Patient } from "./patient.model";
import { Pharmacy } from "./pharmacy.model";

export class Appointment {
    patientName:string;
    patientSurname:string;
    duration:number;
    date:any;
    uidn:string;
    dateRange:any;
    report:string;
    status:number;
    medicine:string[];
    employeeuidn:string;
    patient: Patient;
    id:any;
	medicineQuantity:string;
    pharmacy:Pharmacy;
}