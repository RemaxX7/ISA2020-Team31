import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { CounselingService } from 'src/app/service/counseling.service';
import { RatingDialogComponent } from '../rating-dialog/rating-dialog.component';

@Component({
  selector: 'app-past-counselings',
  templateUrl: './past-counselings.component.html',
  styleUrls: ['./past-counselings.component.css']
})
export class PastCounselingsComponent implements OnInit {

  public counselings: Array<any>;
  public totalElements: number;
  public selected: string = 'dateRange.startDateTime'
  public currentPage: number = 0;

  constructor(private counselingService: CounselingService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.GetCounselings(0, 'dateRange.startDateTime');
  }

  GetCounselings(page: number, sort: string) {
    this.counselingService.getPageFinished(page, sort).subscribe(
      data => {
        this.counselings = data['content'];
        this.totalElements = data['totalElements'];
      }
    )
  }

  NextPage(event: PageEvent) {
    this.GetCounselings(event.pageIndex, this.selected);
    this.currentPage = event.pageIndex;
  }

  ParseDate(date: Array<number>) {
    return new Date(date[0], date[1]-1, date[2], date[3], date[4]).toString().slice(0, 24);
  }

  Sort() {
    this.GetCounselings(this.currentPage, this.selected);
  }

  OpenDialog(employeeId) {
    this.dialog.open(RatingDialogComponent, {
      data: {
        type: 'employee',
        target: employeeId
      }
    });
  }
}
