import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ComplaintService } from 'src/app/service/complaint.service';
import { PharmacyService } from 'src/app/service/pharmacy.service';
import { PharmacyComplaintDTO } from '../dto/pharmacy-complaint-dto.model';

@Component({
  selector: 'app-pharmacy-complaint',
  templateUrl: './pharmacy-complaint.component.html',
  styleUrls: ['./pharmacy-complaint.component.css']
})
export class PharmacyComplaintComponent implements OnInit {

  constructor(private pharmacyService: PharmacyService, private complaintService: ComplaintService, private fb: FormBuilder, private toastr: ToastrService) { }

  public pharmacies: Array<any>;
  public myForm: FormGroup;

  ngOnInit(): void {
    this.GetPharmacies();
    this.myForm = this.fb.group({
      pharmacy: ['', [Validators.required]],
      text: ['', [Validators.required]]
    })
  }

  GetPharmacies() {
    this.pharmacyService.getAll().subscribe(data =>
      this.pharmacies = data
    )
  }

  Send() {
    let complaint = new PharmacyComplaintDTO();
    complaint.pharmacyId = this.myForm.controls['pharmacy'].value.id;
    complaint.complaintText = this.myForm.controls['text'].value;

    this.complaintService.makePharmacyComplaint(complaint).subscribe(data => {
      this.toastr.success('Complaint for ' + data.pharmacy.name + ' submitted.');
    },
    err => {
      this.toastr.error(err.error);
    })
  }

}
