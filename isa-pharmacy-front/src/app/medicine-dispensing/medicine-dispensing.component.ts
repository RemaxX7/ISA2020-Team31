import { Component, OnInit } from '@angular/core';
import { MedicineReservation } from '../model/medicineReservation.model';
import { EmployeeService } from '../service/employee-service';

@Component({
  selector: 'app-medicine-dispensing',
  templateUrl: './medicine-dispensing.component.html',
  styleUrls: ['./medicine-dispensing.component.css']
})
export class MedicineDispensingComponent implements OnInit {

  constructor(private service:EmployeeService) { }
  patientReservation:MedicineReservation[]=[];
  ngOnInit(): void {
    //this.HideTable();
  }
  LoadMedicineReservation(reservation){
    let user = JSON.parse(localStorage.getItem("user"));
    this.service.loadReservation(reservation,user.uidn).subscribe(data=>this.patientReservation=data,err => {
      alert("Reservation not found or expired.");
      window.location.reload();
    } )
  
  }
  FinalizeReservation(code){
    this.service.finalizeReservation(code).subscribe(()=>alert("Reservation picked up."),err=>{alert('Alert For your User!');})
    
  }
  HideTable(){
    var table = document.getElementById("myTable");
    table.style.display = "none";
  }
  ShowTable(){
    var table = document.getElementById("myTable");
    table.style.display = "";
  }
  MyFunction(){
    var input, filter, table, tr, i,td1;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    let novi:string = filter;
    if(novi==''){
      this.HideTable();
    }else{
      this.ShowTable();
    }
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td1 = tr[i].getElementsByTagName("td")[2];
        if(td1){
        if (td1.innerHTML.toUpperCase().indexOf(filter) > -1 ) {
            tr[i].style.display = "";
        } else {
            tr[i].style.display = "none";
        } 
    }
  }
}
}
