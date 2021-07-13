import { Component, OnInit } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { NzMessageService } from 'ng-zorro-antd/message';
import { JobDetailComponent } from '../job/job-detail/job-detail.component';
import { JobSaveComponent } from '../job/job-save/job-save.component';
import { ApiJobLog } from './apiJobLog';
import { JobLogDetailComponent } from './job-log-detail/job-log-detail.component';

@Component({
  selector: 'app-job-log',
  templateUrl: './job-log.component.html',
  styleUrls: ['./job-log.component.css'],
})
export class JobLogComponent implements OnInit {


  checked = false;
  loading = false;
  indeterminate = false;
  listOfData: any[] = [];
  listOfCurrentPageData: any[] = [];
  setOfCheckedId = new Set<string>();

  updateCheckedSet(jobLogId: string, checked: boolean): void {
    if (checked) {
      this.setOfCheckedId.add(jobLogId);
    } else {
      this.setOfCheckedId.delete(jobLogId);
    }
  }

  onCurrentPageDataChange(listOfCurrentPageData: any[]): void {
    this.listOfCurrentPageData = listOfCurrentPageData;
    this.refreshCheckedStatus();
  }

  refreshCheckedStatus(): void {
    const listOfEnabledData = this.listOfCurrentPageData;
    this.checked = listOfEnabledData.every(({ jobLogId }) => this.setOfCheckedId.has(jobLogId));
    this.indeterminate = listOfEnabledData.some(({ jobLogId }) => this.setOfCheckedId.has(jobLogId)) && !this.checked;
  }

  onItemChecked(jobLogId: string, checked: boolean): void {
    this.updateCheckedSet(jobLogId, checked);
    this.refreshCheckedStatus();
  }

  onAllChecked(checked: boolean): void {
    this.listOfCurrentPageData.forEach(({ jobLogId }) => this.updateCheckedSet(jobLogId, checked));
    this.refreshCheckedStatus();
  }

  constructor(
    private http: _HttpClient,
    private message: NzMessageService,
    private model: ModalHelper,
  ) {
  }

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.loading = true;
    this.http.get(ApiJobLog.list).subscribe(res => {
      if (res.success) {
        this.listOfData = res.result;
        this.checked = false;
        this.indeterminate = false;
        this.setOfCheckedId = new Set<string>();
      } else {
        this.message.error(res.message);
      }
      this.loading = false;
    });
  }

  detail(data: any) {
    this.model.create(JobLogDetailComponent, { record: data }).subscribe(res => this.load());
  }

  delete() {
    if ((this.indeterminate || this.checked) && this.setOfCheckedId) {
      let ids = '';
      for (const jobLogId of this.setOfCheckedId) {
        ids += jobLogId + ',';
      }
      ids = ids.substr(0, ids.length - 1);
      this.http.post(ApiJobLog.remove, { ids }).subscribe(res => {
        if (res.success) {
          this.load();
        } else {
          this.message.error(res.message);
        }
      });
    }
  }

}
