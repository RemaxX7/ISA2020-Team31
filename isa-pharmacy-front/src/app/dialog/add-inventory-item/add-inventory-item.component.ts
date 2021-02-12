import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { InventoryItemCreate } from 'src/app/dto/inventory-item-create.model';
import { Medicine } from 'src/app/model/medicine.model';
import { MedicineService } from 'src/app/service/medicine.service';
import { PharmacyService } from 'src/app/service/pharmacy.service';

@Component({
  selector: 'app-add-inventory-item',
  templateUrl: './add-inventory-item.component.html',
  styleUrls: ['./add-inventory-item.component.css']
})
export class AddInventoryItemComponent implements OnInit {

  constructor(private pharmacyService:PharmacyService,private medicineService:MedicineService , public dialogRef:MatDialogRef<AddInventoryItemComponent>,
    @Inject( MAT_DIALOG_DATA) public data:{
      pharmacyId:number;
    }) { }
  
  medID:number;
  quantityFormControl:FormControl;
  medicines:Medicine[]=[];
  ngOnInit(): void {
    this.quantityFormControl=new FormControl('',Validators.required);
    this.FillMedicines();
  }

  async FillMedicines(){
    await this.medicineService.getAllMedicine().then(
      data=>this.medicines=data
    );
  }
  
  dto:InventoryItemCreate;
  error:string;
  
  AddItem(){
    this.dto=new InventoryItemCreate;
    this.dto.medicineId=this.medID;
    this.dto.pharmacyId=this.data.pharmacyId;
    this.dto.quantity=this.quantityFormControl.value;
    this.pharmacyService.addItem(this.dto).subscribe(
      res=>this.dialogRef.close(),
      err=>this.error=err.error
    )

    

  }
  onNoClick(): void {
    this.dialogRef.close();
  }
}
