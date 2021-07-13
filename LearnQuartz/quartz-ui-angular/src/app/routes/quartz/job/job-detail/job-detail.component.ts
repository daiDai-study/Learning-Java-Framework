import { Component, Input, OnInit } from '@angular/core';
import { NzModalRef } from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-job-detail',
  templateUrl: './job-detail.component.html',
  styles: [
  ]
})
export class JobDetailComponent implements OnInit {

  @Input()
  record: any;

  constructor(
    private modelRef: NzModalRef,
  ) { }

  ngOnInit(): void {
  }

  close() {
    this.modelRef.destroy();
  }
}
