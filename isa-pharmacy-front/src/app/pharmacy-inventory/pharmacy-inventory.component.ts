import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AddInventoryItemComponent } from '../dialog/add-inventory-item/add-inventory-item.component';
import { InventoryItem } from '../model/inventory-item.model';
import { Pharmacy } from '../model/pharmacy.model';
import { PharmacyService } from '../service/pharmacy.service';

@Component({
  selector: 'app-pharmacy-inventory',
  templateUrl: './pharmacy-inventory.component.html',
  styleUrls: ['./pharmacy-inventory.component.css']
})
export class PharmacyInventoryComponent implements OnInit {

  constructor(private router: Router,public dialog: MatDialog,private pharmacyService:PharmacyService) { }

  pharmacy:Pharmacy=new Pharmacy;
  pharmacyId:number;
  inventoryItems:InventoryItem[]=[];
  ngOnInit(): void {
    this.pharmacyId=1;
    this.FillInventory();
  }

  LogOut() {
    localStorage.removeItem('userToken');
    localStorage.removeItem('user');
    this.router.navigate(['login']);
  }
  FillInventory(){
    this.pharmacyService.getPharmacy(this.pharmacyId).then(
      data=>{
        this.pharmacy=data;
        this.inventoryItems=this.pharmacy.inventory;
      }
    )
  }

  AddItem()
  {
    const dialogRef = this.dialog.open(AddInventoryItemComponent,{
      data:{
        pharmacyId:this.pharmacyId
      }
    });

    dialogRef.afterClosed().subscribe(result => {
     this.ngOnInit();
    });
  }

}
