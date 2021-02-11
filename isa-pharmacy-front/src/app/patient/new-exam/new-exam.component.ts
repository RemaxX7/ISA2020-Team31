import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { ExamService } from 'src/app/service/exam.service';

@Component({
  selector: 'app-new-exam',
  templateUrl: './new-exam.component.html',
  styleUrls: ['./new-exam.component.css']
})
export class NewExamComponent implements OnInit {

  public exams: Array<any>;
  public totalElements: number;
  public selected: string = 'dateRange.startDateTime'
  public currentPage: number = 0;

  constructor(private examService: ExamService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.GetExams(0, 'dateRange.startDateTime');
  }

  GetExams(page: number, sort: string) {
    this.examService.getPageFree(page, sort).subscribe(
      data => {
        console.log(data);
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

  Schedule(exam: any) {
    this.examService.schedule(exam.id).subscribe(data => {
      this.toastr.success('Exam scheduled.');
      this.GetExams(this.currentPage, this.selected);
    },
    err => {
      this.toastr.error(err.error);
    })
  }
}