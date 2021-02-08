import { Medicine } from "./medicine.model";

export class Appointment {
    patientName:string;
    patientSurname:string;
    duration:number;
    date:string;
    uidn:string;
    report:string;
    status:number;
    medicine:string[]=[];
	
}