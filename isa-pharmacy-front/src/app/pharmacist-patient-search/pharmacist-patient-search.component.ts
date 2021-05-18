import { Component, OnInit } from '@angular/core';
import { Pharmacist } from '../model/pharmacist.model';
import { EmployeeService } from '../service/employee-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-pharmacist-patient-search',
  templateUrl: './pharmacist-patient-search.component.html',
  styleUrls: ['./pharmacist-patient-search.component.css']
})
export class PharmacistPatientSearchComponent implements OnInit {

  users:any
  constructor(private service:EmployeeService,private userService:UserService) { }

  ngOnInit(): void {
    this.service.refreshJWTToken();
    this.FindCheckedPatients();
  }
  async FillPatients(){
    this.service.refreshJWTToken();
    await this.service.getAllUsers().then(
      data=>this.users=data
      
    )
    console.log(this.users);
  }
  async FindCheckedPatients(){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem("user"));
    await this.service.findCheckedPatients(user.uidn).then(
      data=>this.users=data
    )
  }
  MyFunction(){
    var input, filter, table, tr, td, i,td1;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    let novi:string = filter;
    if(novi==''){
      this.FillPatients();
    }
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        td1 = tr[i].getElementsByTagName("td")[1];
        if(td){
        if (td.innerHTML.toUpperCase().indexOf(novi.split(' ')[0]) > -1 && td1.innerHTML.toUpperCase().indexOf(novi.split(' ')[1]) > -1 ) {
            tr[i].style.display = "";
            tr[i].getElementsByTagName("td")[2].style.display="none";
            tr[i].getElementsByTagName("td")[3].style.display="none";
        } else {
            tr[i].style.display = "none";
        }
    }
  }
}
  Reload(){
    window.location.reload();
  }
  CompareValues(a, b) {
    return (a<b) ? -1 : (a>b) ? 1 : 0;
  }
sortTable(colnum) {
  let rows = Array.from(document.getElementById("myTable").querySelectorAll('tr'));

  rows = rows.slice(1);
  let qs = `td:nth-child(${colnum}`;

  rows.sort( (r1,r2) => {
    let t1 = r1.querySelector(qs);
    let t2 = r2.querySelector(qs);

    return this.CompareValues(t1.textContent,t2.textContent);
  });

  rows.forEach(row => document.getElementById("myTable").appendChild(row));
}
LogOut() {
  this.userService.Logout().subscribe(data => {
  },
    err => console.log(err)
  )
}
}