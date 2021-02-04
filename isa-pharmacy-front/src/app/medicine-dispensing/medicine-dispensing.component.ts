import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-medicine-dispensing',
  templateUrl: './medicine-dispensing.component.html',
  styleUrls: ['./medicine-dispensing.component.css']
})
export class MedicineDispensingComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    this.HideTable();
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
