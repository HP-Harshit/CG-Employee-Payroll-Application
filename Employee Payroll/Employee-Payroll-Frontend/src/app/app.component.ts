import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface Employee {
  id?: number;
  name?: string;
  gender?: string;
  department?: string[];
  salary?: number;
  day?: number;
  month?: string;
  year?: number;
  profilePic?: string;
  startDate?: string;
  note?: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  imports: [CommonModule, FormsModule],
})
export class AppComponent implements OnInit {
  title = 'Employee Payroll';
  employees: Employee[] = [];
  searchText: string = '';
  showSearch: boolean = false;

  newEmployee: Employee = {
    name: '',
    gender: '',
    department: [],
    salary: undefined,
    day: undefined,
    month: '',
    year: undefined,
    profilePic: '',
    startDate: '',
    note: '',
  };
  editMode: boolean = false;
  employeeToEdit: Employee | null = null;
  showForm: boolean = false;

  days: number[] = Array.from({ length: 31 }, (_, i) => i + 1);
  months: string[] = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December',
  ];
  years: number[] = Array.from({ length: 50 }, (_, i) => i + 1970);

  private apiUrl = 'http://localhost:8080/employeepayrollservice';

  constructor() {}

  ngOnInit(): void {
    this.fetchEmployees();
  }

  async fetchEmployees() {
    try {
      const response = await fetch(`${this.apiUrl}/`);
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      this.employees = await response.json();
      console.log('Data received:', this.employees);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  }

  async sendPostRequest(employee: Employee) {
    try {
      const response = await fetch(`${this.apiUrl}/create`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(employee),
      });
      if (!response.ok) {
        throw new Error('Failed to create employee');
      }
      const data = await response.json();
      console.log('Success:', data);
      this.fetchEmployees(); // Ensure the data is fetched after adding a new employee
    } catch (error) {
      console.error('Error:', error);
    }
  }

  addEmployee(): void {
    this.newEmployee = {
      name: '',
      gender: '',
      department: [],
      salary: undefined,
      day: undefined,
      month: '',
      year: undefined,
      profilePic: '',
      startDate: '',
      note: '',
    };
    this.editMode = false;
    this.showForm = true;
  }

  async deleteEmployee(id: number | undefined) {
    if (!id) {
      console.error('Invalid ID');
      return;
    }
    try {
      const response = await fetch(`${this.apiUrl}/delete/${id}`, {
        method: 'DELETE',
      });
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      console.log('Record deleted successfully');
      this.fetchEmployees(); // Refresh the data after deletion
    } catch (error) {
      console.error('Error:', error);
    }
  }

  editEmployee(employee: Employee): void {
    this.employeeToEdit = employee;
    this.newEmployee = { ...employee };
    this.editMode = true;
    this.showForm = true;
  }

  formatDate(
    day: number | undefined,
    month: string,
    year: number | undefined
  ): string {
    if (!day || !month || !year) return '';
    const date = new Date(`${month} ${day}, ${year}`);
    return date.toISOString().split('T')[0]; // Converts to yyyy-MM-dd format
  }

  async saveEmployee() {
    if (
      !this.newEmployee.name ||
      !this.newEmployee.gender ||
      !this.newEmployee.department?.length ||
      !this.newEmployee.salary ||
      !this.newEmployee.day ||
      !this.newEmployee.month ||
      !this.newEmployee.year ||
      !this.newEmployee.profilePic
    ) {
      alert('Please fill in all the fields.');
      return;
    }

    this.newEmployee.startDate = this.formatDate(
      this.newEmployee.day,
      this.newEmployee.month,
      this.newEmployee.year
    );

    if (this.editMode && this.employeeToEdit && this.employeeToEdit.id) {
      try {
        const response = await fetch(
          `${this.apiUrl}/update/${this.employeeToEdit.id}`,
          {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(this.newEmployee),
          }
        );
        if (!response.ok) {
          throw new Error('Failed to update employee');
        }
        const data = await response.json();
        console.log('Record updated successfully:', data);
        this.fetchEmployees(); // Ensure the data is fetched after updating an employee
      } catch (error) {
        console.error('Error:', error);
      }
    } else {
      this.sendPostRequest(this.newEmployee);
    }
    this.resetForm();
  }

  resetForm(): void {
    this.newEmployee = {
      name: '',
      gender: '',
      department: [],
      salary: undefined,
      day: undefined,
      month: '',
      year: undefined,
      profilePic: '',
      startDate: '',
      note: '',
    };
    this.editMode = false;
    this.showForm = false;
    this.employeeToEdit = null;
  }

  toggleSearch(): void {
    this.showSearch = !this.showSearch;
  }

  updateDepartments(dept: string, event: Event): void {
    const checked = (event.target as HTMLInputElement).checked;
    if (checked) {
      this.newEmployee.department?.push(dept);
    } else {
      this.newEmployee.department = this.newEmployee.department?.filter(
        (d) => d !== dept
      );
    }
  }

  searchEmployees(): Employee[] {
    const search = this.searchText.toLowerCase();
    return this.employees.filter(
      (employee) =>
        employee.name?.toLowerCase().includes(search) ||
        employee.id?.toString().includes(search) ||
        employee.department?.some((dept) => dept.toLowerCase().includes(search))
    );
  }
}
