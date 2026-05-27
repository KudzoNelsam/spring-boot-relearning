import { Component, inject, signal } from '@angular/core';
import { TaskList } from './task-list/task-list';
import { TaskRequest } from './task-request';
import { form, FormField, minLength, required } from '@angular/forms/signals';
import { TaskService } from './task-service';
import { ApiResponse } from '../shared/api-response';
import { ApiPaginatedResponse } from '../shared/api-paginated-response';
import { TaskModel } from './task-model';

@Component({
  selector: 'app-task',
  imports: [TaskList, FormField],
  templateUrl: './task.html',
  styleUrl: './task.css',
})
export class Task {
  // TaskService
  taskService = inject(TaskService);
  // Creationd du model du formulaire
  taskModel = signal<TaskRequest>({ title: '', description: '' });
  tasks = signal<TaskModel[] | []>([]);

  // Creation du formulaire
  taskForm = form(this.taskModel, (f) => {
    required(f.title, { message: 'Le titre est obligatoire' });
    required(f.description, { message: 'La description est obligatoire' });
    minLength(f.description, 5, { message: 'La description doit avoir au moins 5 caracteres' });
  });
  protected saveTask() {
    this.taskService.getAllTasks().subscribe({
      next: (resp: ApiPaginatedResponse<TaskModel[]>) => {
        this.tasks.set(resp.data);
        // this.loading.set(false);
        // this.pages.set(resp.pages);
        // this.current_page.set(resp.page);
        // this.current_size.set(resp.size);
      },
      error: (err) => {},
      complete: () => {
        // this.loading.set(false);
      },
    });
    if (this.taskForm().invalid()) {
      this.taskForm.title().markAsTouched();
      this.taskForm.description().markAsTouched();
      return;
    }
    this.taskService.createTask(this.taskModel()).subscribe({
      next: (res: ApiResponse<String>) => {
        console.log(res.data);
      },
    });
  }
}
