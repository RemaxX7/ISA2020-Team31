import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PricelistItemMedicineCreate } from 'src/app/dto/pricelist-item-medicine-create.model';
import { SetMedicinePrice } from 'src/app/dto/set-medicine-price.model';
import { PricelistService } from 'src/app/service/pricelist.service';
import { AddShiftPharmacistComponent } from '../add-shift-pharmacist/add-shift-pharmacist.component';

@Component({
  selector: 'app-change-price',
  templateUrl: './change-price.component.html',
  styleUrls: ['./change-price.component.css']
})
export class ChangePriceComponent implements OnInit {

  constructor(private pricelistService:PricelistService, public dialogRef:MatDialogRef<ChangePriceComponent>,
    @Inject( MAT_DIALOG_DATA) public data:{
      itemId:number;
      pricelistId:number;
    }) { }
  priceFormControl:FormControl;

  ngOnInit(): void {
    this.priceFormControl = new FormControl('', [
      Validators.required
    ]);
  }

  priceDto:SetMedicinePrice=new SetMedicinePrice;
  dto:PricelistItemMedicineCreate=new PricelistItemMedicineCreate;
  Confirm(){
    
      this.dto.medicineId=0;
      this.dto.price=this.priceFormControl.value;
      this.dto.startDateTime=new Date();
      this.dto.startDateTime.setHours(this.dto.startDateTime.getHours()+1);
      this.priceDto.pricelistId=this.data.pricelistId;
      this.priceDto.itemId=this.data.pricelistId;
      this.priceDto.dto=this.dto;
      this.pricelistService.SetNewMedicinePrice(this.priceDto).subscribe(
          data=>{console.log("Uspesno promenjen");
          this.dialogRef.close();  
        }
      )
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  
}
