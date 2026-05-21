import { Component } from '@angular/core';
import { TaskList } from './task-list/task-list';

@Component({
  selector: 'app-task',
  imports: [TaskList],
  templateUrl: './task.html',
  styleUrl: './task.css',
})
export class Task {}
