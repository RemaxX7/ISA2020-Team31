import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { AddShiftPharmacistComponent } from '../dialog/add-shift-pharmacist/add-shift-pharmacist.component';
import { ChangePriceComponent } from '../dialog/change-price/change-price.component';
import { PricelistMedicineItem } from '../model/pricelist-medicine-item.model';
import { Pricelist } from '../model/pricelist.model';
import { PricelistService } from '../service/pricelist.service';

@Component({
  selector: 'app-pricelist',
  templateUrl: './pricelist.component.html',
  styleUrls: ['./pricelist.component.css']
})
export class PricelistComponent implements OnInit {

  constructor(private pricelistService:PricelistService,public dialog: MatDialog, private route: ActivatedRoute) { }

  pharmacyId:number;
  pricelist:Pricelist=new Pricelist;
  pricelistMedicineItems:PricelistMedicineItem[]=[];
  ngOnInit(): void {
    this.pharmacyId=1;
    this.GetPricelist();
    
  }

  ChangePrice(itemId:number){

    console.log(itemId);
    const dialogRef = this.dialog.open(ChangePriceComponent,{
      data:{
        itemId:itemId,
        pricelistId:this.pricelist.id
      }
    });

    dialogRef.afterClosed().subscribe(result => {
     this.ngOnInit();
    });
  }

  GetPricelist(){
      this.pricelistService.GetPricelistByPharmacyId(this.pharmacyId).subscribe(
        data=>{
          this.pricelist=data;
          this.FillPricelistItem();
        }
      )
  }

  FillPricelistItem(){
    this.pricelistMedicineItems=[];
    this.pricelistService.GetAllActiveMedicineItem(this.pricelist.id).subscribe(
      data=>{
        this.pricelistMedicineItems=data;
      }
    )
  }

}