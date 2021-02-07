import { Pharmacy } from "./pharmacy.model";

export class Dermatologist {
    id:number;
    name:string;
    surname:string;
    email:string;
    pharmacies:Pharmacy[];
}
