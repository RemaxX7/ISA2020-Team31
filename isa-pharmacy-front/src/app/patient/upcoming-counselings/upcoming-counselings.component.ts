import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { CounselingService } from 'src/app/service/counseling.service';

@Component({
  selector: 'app-upcoming-counselings',
  templateUrl: './upcoming-counselings.component.html',
  styleUrls: ['./upcoming-counselings.component.css']
})
export class UpcomingCounselingsComponent implements OnInit {

  public counselings: Array<any>;
  public totalElements: number;
  public selected: string = 'dateRange.startDateTime'
  public currentPage: number = 0;

  constructor(private counselingService: CounselingService) { }

  ngOnInit(): void {
    this.GetCounselings(0, 'dateRange.startDateTime');
  }

  GetCounselings(page: number, sort: string) {
    this.counselingService.getPageCreated(page, sort).subscribe(
      data => {
        console.log(data);
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
    return new Date(date[0], date[1], date[2], date[3], date[4]).toString().slice(0, 24);
  }

  Sort() {
    this.GetCounselings(this.currentPage, this.selected);
  }
}
