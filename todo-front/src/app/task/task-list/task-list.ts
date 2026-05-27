import { Component, inject, Input, OnInit, signal } from '@angular/core';
import { TaskService } from '../task-service';
import { ApiPaginatedResponse } from '../../shared/api-paginated-response';
import { TaskModel } from '../task-model';
import { List } from 'postcss/lib/list';

@Component({
  selector: 'app-task-list',
  imports: [],
  templateUrl: './task-list.html',
  styleUrl: './task-list.css',
})
export class TaskList implements OnInit {
  private taskService = inject(TaskService);

  @Input() tasksList = signal<TaskModel[] | []>([]);
  // tasks = signal<TaskModel[] | []>([]);
  pages = signal<number[]>([]);
  loading = signal(false);
  current_page = signal<number>(0);
  current_size = signal<number>(5);

  ngOnInit(): void {
    // this.loading.set(true);

  }
}
