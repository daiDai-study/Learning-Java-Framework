import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { _HttpClient } from '@delon/theme';
import { NzModalRef } from 'ng-zorro-antd/modal';
import { ApiJob } from '../apiJob';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-job-save',
  templateUrl: './job-save.component.html',
  styles: [],
})
export class JobSaveComponent implements OnInit {

  @Input()
  add: boolean;
  @Input()
  record: any;
  data: any;

  validateForm!: FormGroup;

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
    this.save();
  }

  constructor(
    private fb: FormBuilder,
    public http: _HttpClient,
    private modelRef: NzModalRef,
    private message: NzMessageService,
  ) {
  }

  ngOnInit(): void {
    if (this.add) {
      this.data = {};
      this.data.jobGroup = 'DEFAULT';
      this.data.misfirePolicy = '3';
      this.data.concurrent = '1';
      this.data.status = '0';
    } else {
      this.data = this.record;
    }
    this.validateForm = this.fb.group({
      jobName: [null, [Validators.required]],
      jobGroup: ['DEFAULT'],
      invokeTarget: [null, [Validators.required]],
      cronExpression: [null, [Validators.required]],
      misfirePolicy: ['3'],
      concurrent: ['1'],
      status: ['0'],
    });
  }

  close() {
    this.modelRef.destroy();
  }

  save() {
    const url = this.add ? ApiJob.add : ApiJob.edit;
    this.http.post(url, this.data).subscribe(res => {
      if (res.success) {
        this.modelRef.close(true);
      } else {
        this.message.error(res.message);
      }
    });
  }
}
