import { Pharmacy } from "./pharmacy.model";

export class Dermatologist {
    id:number;
    name:string;
    surname:string;
    email:string;
    username:string;
    uidn:string;
    pharmacies:Pharmacy[];
}
