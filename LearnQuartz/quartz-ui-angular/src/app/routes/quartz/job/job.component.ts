import { Component, OnInit } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';
import { ApiJob } from './apiJob';
import { JobDetailComponent } from './job-detail/job-detail.component';
import { JobSaveComponent } from './job-save/job-save.component';

@Component({
  selector: 'app-job',
  templateUrl: './job.component.html',
  styleUrls: ['./job.component.css'],
})
export class JobComponent implements OnInit {

  checked = false;
  loading = false;
  switchLoading = false;
  indeterminate = false;
  listOfData: any[] = [];
  listOfCurrentPageData: any[] = [];
  setOfCheckedId = new Set<string>();

  updateCheckedSet(jobId: string, checked: boolean): void {
    if (checked) {
      this.setOfCheckedId.add(jobId);
    } else {
      this.setOfCheckedId.delete(jobId);
    }
  }

  onCurrentPageDataChange(listOfCurrentPageData: any[]): void {
    this.listOfCurrentPageData = listOfCurrentPageData;
    this.refreshCheckedStatus();
  }

  refreshCheckedStatus(): void {
    const listOfEnabledData = this.listOfCurrentPageData;
    this.checked = listOfEnabledData.every(({ jobId }) => this.setOfCheckedId.has(jobId));
    this.indeterminate = listOfEnabledData.some(({ jobId }) => this.setOfCheckedId.has(jobId)) && !this.checked;
  }

  onItemChecked(jobId: string, checked: boolean): void {
    this.updateCheckedSet(jobId, checked);
    this.refreshCheckedStatus();
  }

  onAllChecked(checked: boolean): void {
    this.listOfCurrentPageData.forEach(({ jobId }) => this.updateCheckedSet(jobId, checked));
    this.refreshCheckedStatus();
  }

  constructor(
    private http: _HttpClient,
    private message: NzMessageService,
    private model: ModalHelper,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.loading = true;
    this.http.get(ApiJob.list).subscribe(res => {
      if (res.success) {
        this.listOfData = res.result;
      } else {
        this.message.error(res.message);
      }
      this.loading = false;
    });
  }


  clickSwitch(data: any): void {
    const temp = data;
    temp.status = temp.status === '0' ? '1' : '0';
    this.http.post(ApiJob.changeStatus, data).subscribe(res => {
      if (res.success) {
        this.message.success('状态修改成功');
        data.status = temp.status;
      } else {
        this.message.error(res.message);
      }
    });
  }

  run(data: any) {
    this.http.post(ApiJob.run, data).subscribe(res => {
      if (res.success) {
        this.message.success('执行成功');
      } else {
        this.message.error(res.message);
      }
    });
  }

  detail(data: any) {
    this.model.create(JobDetailComponent, { record: data }).subscribe(res => this.load());
  }

  add() {
    this.model.create(JobSaveComponent, { add: true }).subscribe(res => this.load());
  }

  edit(data: any) {
    this.model.create(JobSaveComponent, { add: false, record: data }).subscribe(res => this.load());
  }

  delete(jobId: string) {
    this.http.post(ApiJob.remove, {ids: jobId}).subscribe(res => {
      if(res.success){
        this.load();
      }else{
        this.message.error(res.message);
      }
    })
  }

  goToJobLog() {
    this.router.navigateByUrl('quartz/job-log');
  }

}
