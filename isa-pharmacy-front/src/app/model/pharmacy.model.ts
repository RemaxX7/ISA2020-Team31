import { Address } from "./address.model";

export class Pharmacy {
    id:number;
    name:string;
    address:Address=new Address;
    rate:number;
}
