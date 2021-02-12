import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ComplaintService } from 'src/app/service/complaint.service';
import { EmployeeService } from 'src/app/service/employee-service';
import { EmployeeComplaintDTO } from '../dto/employee-complaint-dto.model';

@Component({
  selector: 'app-employee-complaint',
  templateUrl: './employee-complaint.component.html',
  styleUrls: ['./employee-complaint.component.css']
})
export class EmployeeComplaintComponent implements OnInit {

  constructor(private employeeService: EmployeeService, private complaintService: ComplaintService, private fb: FormBuilder, private toastr: ToastrService) { }

  public employees: Array<any>;
  public myForm: FormGroup;

  ngOnInit(): void {
    this.GetPharmacies();
    this.myForm = this.fb.group({
      employee: ['', [Validators.required]],
      text: ['', [Validators.required]]
    })
  }

  GetPharmacies() {
    this.employeeService.getAllEmployees().subscribe(data =>
      this.employees = data
    )
  }

  Send() {
    let complaint = new EmployeeComplaintDTO();
    complaint.employeeId = this.myForm.controls['employee'].value.id;
    complaint.complaintText = this.myForm.controls['text'].value;

    this.complaintService.makeEmployeeComplaint(complaint).subscribe(data => {
      this.toastr.success('Complaint for ' + data.employee.name + ' submitted.');
      this.myForm.reset();
    },
    err => {
      this.toastr.error(err.error);
    })
  }
}