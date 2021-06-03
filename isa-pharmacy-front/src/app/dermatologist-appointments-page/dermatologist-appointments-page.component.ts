import { Component, OnInit } from '@angular/core';
import { Appointment } from '../model/appointment.model';
import { Dermatologist } from '../model/dermatologist.model';
import { Patient } from '../model/patient.model';
import { EmployeeService } from '../service/employee-service';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-dermatologist-appointments-page',
  templateUrl: './dermatologist-appointments-page.component.html',
  styleUrls: ['./dermatologist-appointments-page.component.css']
})
export class DermatologistAppointmentsPageComponent implements OnInit {
  users:Patient[]=[]
  derm:any;
  appointments:Appointment[]=[]
  constructor(private service:EmployeeService,private userService:UserService ) {}

  ngOnInit(): void {
    this.service.refreshJWTToken();
    this.FillPatients();
    this.FillExams();
  }

  async FillPatients(){
    this.service.refreshJWTToken();
    await this.service.getAllUsers().then(
      data=>this.users=data
      
    )
  }

  async FillExams(){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem("user"));
    await this.service.fillExams(user.uidn).then(
      data=>this.appointments=data
    )
  }

  PenalizePatient(uidn,dateRange){
    this.service.refreshJWTToken();
    let user = JSON.parse(localStorage.getItem("user"));
    this.service.penalizePatient(uidn,dateRange.startDateTime[0]+"-"+dateRange.startDateTime[1]+"-"+dateRange.startDateTime[2]+"T"+dateRange.startDateTime[3]+":"+dateRange.startDateTime[4],user.uidn).then(()=>{
      alert("User has been punished with 1 negative point.");
      this.Reload();
    });
  }

  sortTableByDate() {
    let rows = Array.from(document.getElementById("myTable").querySelectorAll('tr'));

    rows = rows.slice(1);
    let qs = `td:nth-child(${6}`;

    rows.sort( (r1,r2) => {
      let t1 = r1.querySelector(qs);
      let t2 = r2.querySelector(qs);

      return this.CompareValues(t1.textContent,t2.textContent);
    });

    rows.forEach(row => document.getElementById("myTable").appendChild(row));
  }

  MyFunction(){
    var input, filter, table, tr, td, i,td1;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    let novi:string = filter;
    if(novi==''){
      this.Reload();
    }
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        td1 = tr[i].getElementsByTagName("td")[1];
        if(td){
        if (td.innerHTML.toUpperCase().indexOf(novi.split(' ')[0]) > -1 && td1.innerHTML.toUpperCase().indexOf(novi.split(' ')[1]) > -1 ) {
            tr[i].style.display = "";
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
