import { Component, inject, OnInit, signal } from '@angular/core';
import { TaskService } from '../task-service';
import { ApiPaginatedResponse } from '../../shared/api-paginated-response';
import { TaskModel } from '../task-model';

@Component({
  selector: 'app-task-list',
  imports: [],
  templateUrl: './task-list.html',
  styleUrl: './task-list.css',
})
export class TaskList implements OnInit {
  private taskService = inject(TaskService);

  tasks = signal<TaskModel[] | []>([]);
  pages = signal<number[]>([]);
  loading = signal(false);
  current_page = signal<number>(0);
  current_size = signal<number>(5);

  ngOnInit(): void {
    this.loading.set(true);
    this.taskService.getAllTasks().subscribe({
      next: (resp: ApiPaginatedResponse<TaskModel[]>) => {
        this.tasks.set(resp.data);
        this.loading.set(false);
        this.pages.set(resp.pages);
        this.current_page.set(resp.page)
        this.current_size.set(resp.size)
      },
      error: (err) => {},
      complete: () => {
        this.loading.set(false);
      },
    });
  }
}
