import { Address } from "./Location/address.model";

export class User {
    name: string;
    surname: string;
    username: string;
    uidn: string;
    email: string;
    password: string;
    phoneNumber: string;
    address: Address;
}
