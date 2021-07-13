import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Exception403Component } from '../exception/403.component';
import { JobComponent } from './job/job.component';
import { JobLogComponent } from './job-log/job-log.component';

const routes: Routes = [
  { path: 'job', component: JobComponent },
  { path: 'job-log', component: JobLogComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QuartzRoutingModule { }
