import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { ExamService } from 'src/app/service/exam.service';

@Component({
  selector: 'app-upcoming-exams',
  templateUrl: './upcoming-exams.component.html',
  styleUrls: ['./upcoming-exams.component.css']
})
export class UpcomingExamsComponent implements OnInit {

  public exams: Array<any>;
  public totalElements: number;
  public selected: string = 'dateRange.startDateTime'
  public currentPage: number = 0;

  constructor(private examService: ExamService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.GetExams(0, 'dateRange.startDateTime');
  }

  GetExams(page: number, sort: string) {
    this.examService.getPageCreated(page, sort).subscribe(
      data => {
        this.exams = data['content'];
        this.totalElements = data['totalElements'];
      }
    )
  }

  NextPage(event: PageEvent) {
    this.GetExams(event.pageIndex, this.selected);
    this.currentPage = event.pageIndex;
  }

  ParseDate(date: Array<number>) {
    return new Date(date[0], date[1]-1, date[2], date[3], date[4]).toString().slice(0, 24);
  }

  Sort() {
    this.GetExams(this.currentPage, this.selected);
  }

  Cancel(exam: any) {
    this.examService.cancel(exam.id).subscribe(data => {
      this.toastr.success('Exam cancelled.');
      this.GetExams(this.currentPage, this.selected);
    },
    err => {
      this.toastr.error(err.error);
    })
  }
}