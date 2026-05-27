import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { delay, Observable, timeout } from 'rxjs';
import { ApiPaginatedResponse } from '../shared/api-paginated-response';
import { TaskModel } from './task-model';
import { TaskRequest } from './task-request';
import { ApiResponse } from '../shared/api-response';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  private http = inject(HttpClient);
  private apiUrl = environment.apiUrl;

  public getAllTasks(
    page: number = 0,
    size: number = 20,
  ): Observable<ApiPaginatedResponse<TaskModel[]>> {
    return this.http
      .get<ApiPaginatedResponse<TaskModel[]>>(`${this.apiUrl}/tasks?page=${page}&size=${size}`)
      .pipe(delay(2000));
  }


  public createTask(request: TaskRequest): Observable<ApiResponse<String>> {
    return this.http
      .post<ApiResponse<String>>(`${this.apiUrl}/tasks`, request)
  }
}
