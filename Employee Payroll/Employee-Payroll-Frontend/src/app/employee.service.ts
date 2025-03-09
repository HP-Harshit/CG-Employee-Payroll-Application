import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

export interface Employee {
  id?: number;
  name?: string;
  gender?: string;
  department?: string[];
  salary?: number;
  day?: number;
  month?: string;
  year?: number;
  profileImage?: string;
  startDate?: string;
  note?: string;
}

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiUrl}/`);
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiUrl}/get/${id}`);
  }

  createEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(`${this.apiUrl}/create`, employee);
  }

  updateEmployee(id: number, employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.apiUrl}/update/${id}`, employee);
  }

  deleteEmployee(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  searchEmployees(searchText: string): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiUrl}/search/${searchText}`);
  }
}
