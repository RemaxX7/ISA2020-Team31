import { Address } from "./address.model";
import { InventoryItem } from "./inventory-item.model";

export class Pharmacy {
    id:number;
    name:string;
    address:Address=new Address;
    rate:number;
    inventory:InventoryItem[]=[];
}
