import { Component, OnInit } from '@angular/core';
import { MedicineReservation } from '../model/medicineReservation.model';
import { EmployeeService } from '../service/employee-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-medicine-dispensing',
  templateUrl: './medicine-dispensing.component.html',
  styleUrls: ['./medicine-dispensing.component.css']
})
export class MedicineDispensingComponent implements OnInit {

  constructor(private service:EmployeeService,private userService:UserService) { }
  patientReservation:MedicineReservation[]=[];
  ngOnInit(): void {
    this.service.refreshJWTToken();
    //this.HideTable();
  }
  LoadMedicineReservation(reservation){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem("user"));
    if(reservation!=""){
    this.service.loadReservation(reservation,user.uidn).subscribe(data=>this.patientReservation=data,err => {
      alert("Reservation not found,expired or already picked up.");
      window.location.reload();
    } )
  }
  }
  FinalizeReservation(code){
    this.service.refreshJWTToken();
    this.service.finalizeReservation(code).subscribe(()=>{alert("Reservation picked up.");window.location.reload();},err=>{
      alert('An error has occured.Please consult your superior.');
      window.location.reload();
    })
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
LogOut() {
  this.userService.Logout().subscribe(data => {
  },
    err => console.log(err)
  )
}
}
