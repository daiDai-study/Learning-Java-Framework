import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { QuartzRoutingModule } from './quartz-routing.module';
import { JobComponent } from './job/job.component';
import { SharedModule } from '@shared';
import { JobDetailComponent } from './job/job-detail/job-detail.component';
import { JobSaveComponent } from './job/job-save/job-save.component';
import { NzDescriptionsModule } from 'ng-zorro-antd/descriptions';
import { JobLogComponent } from './job-log/job-log.component';
import { JobLogDetailComponent } from './job-log/job-log-detail/job-log-detail.component';

const COMPONENTS = [
  JobComponent,
  JobLogComponent,
];
const CHILD_COMPONENTS = [
];
const MODAL_COMPONENTS = [
  JobDetailComponent,
  JobSaveComponent,
  JobLogDetailComponent,
];

@NgModule({
  declarations: [
    ...COMPONENTS,
    ...CHILD_COMPONENTS,
    ...MODAL_COMPONENTS,
  ],
  imports: [
    CommonModule,
    QuartzRoutingModule,
    SharedModule,
    NzDescriptionsModule,
  ],
  entryComponents: [
    ...MODAL_COMPONENTS,
  ]
})
export class QuartzModule { }
