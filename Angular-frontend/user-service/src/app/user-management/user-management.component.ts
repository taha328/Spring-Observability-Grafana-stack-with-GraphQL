import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  standalone: true,
  styleUrls: ['./user-management.component.css'],
  imports: [CommonModule, FormsModule]
})
export class UserManagementComponent implements OnInit {
  userId: number | undefined = undefined;
  user: User | undefined;
  newUser: User = { username: '' };
  users: User[] = [];
  message: string = '';
  isUserListVisible: boolean = false;

  constructor(private userService: UserService, private http: HttpClient) { }

  ngOnInit(): void {

  }

  toggleUsers(): void {
    if (this.isUserListVisible) {
      this.users = [];
    } else {
      this.userService.getAllUsers().subscribe(
        users => this.users = users,
        error => {
          console.error('Error fetching users:', error);
          this.message = 'Failed to fetch users.';
        }
      );
    }
    this.isUserListVisible = !this.isUserListVisible;
  }

  addUser(): void {
    if (this.newUser.username) {
      this.userService.addUser(this.newUser).subscribe(
        user => {
          this.message = `User added with ID: ${user.id}, Username: ${user.username}`;
          this.newUser = { username: '' };
          this.userId = user.id;
          if (this.isUserListVisible) {
            this.toggleUsers();
          }
        },
        error => {
          console.error('Error adding user:', error);
          this.message = 'Failed to add user.';
        }
      );
    } else {
      this.message = 'Please enter a username.';
    }
  }
}
