import { City } from "./city.model";

export class Address {
     street:String;
	 number:number;
	 city:City=new City;
	 latitude:number;
	 longitude:number;
}
