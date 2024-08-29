import { bootstrapApplication } from '@angular/platform-browser';
import { UserManagementComponent } from './app/user-management/user-management.component';
import { provideHttpClient } from '@angular/common/http';
import { routes } from './app/app.routes';
import {provideRouter} from "@angular/router";
bootstrapApplication(UserManagementComponent, {
  providers: [
    provideHttpClient(),
    provideRouter(routes)
  ],
})
  .catch(err => console.error(err));
