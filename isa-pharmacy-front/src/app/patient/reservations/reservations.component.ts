import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { MedicineReservationService } from 'src/app/service/medicine-reservation.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  public reservations: Array<any>;
  public totalElements: number;
  public currentPage: number = 0;

  constructor(private reservationService: MedicineReservationService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.GetReservations(0);
  }

  GetReservations(page: number) {
    this.reservationService.getPage(page).subscribe(
      data => {
        this.reservations = data['content'];
        this.totalElements = data['totalElements'];
      }
    )
  }

  NextPage(event: PageEvent) {
    this.GetReservations(event.pageIndex);
    this.currentPage = event.pageIndex;
  }

  ParseDate(date: Array<number>) {
    return new Date(date[0], date[1]-1, date[2]).toString().slice(0, 15);
  }

  Cancel(reservation: any) {
    this.reservationService.cancelReservation(reservation.id).subscribe(data => {
      this.toastr.success('Reservation cancelled.');
      this.GetReservations(this.currentPage);
    },
      err => {
        this.toastr.error(err.error);
      }
    )
  }
}