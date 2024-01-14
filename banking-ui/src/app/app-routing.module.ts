import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {UserDashboardComponent} from "./pages/user-dashboard/user-dashboard.component";
import {MyTransactionsComponent} from "./pages/my-transactions/my-transactions.component";
import {MyContactListComponent} from "./pages/my-contact-list/my-contact-list.component";
import {MainPageComponent} from "./pages/main-page/main-page.component";
import {NewTransactionComponent} from "./pages/new-transaction/new-transaction.component";
import {NewContactComponent} from "./pages/new-contact/new-contact.component";
import {ProfileComponent} from "./pages/profile/profile.component";
import {ManageUsersComponent} from "./admin/manage-users/manage-users.component";
import {MainAdminPageComponent} from "./admin/main-admin-page/main-admin-page.component";
import {AdminDashboardComponent} from "./admin/admin-dashboard/admin-dashboard.component";

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'user',
    component: MainPageComponent,
    children: [
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        path: 'dashboard',
        component: UserDashboardComponent
      },
      {
        path: 'my-transactions',
        component: MyTransactionsComponent
      },
      {
        path: 'my-contact-list',
        component: MyContactListComponent
      },
      {
        path: 'new-transaction',
        component: NewTransactionComponent
      },
      {
        path: 'new-contact',
        component: NewContactComponent
      },
      {
        path: 'profile',
        component: ProfileComponent
      },
    ]
  },
  {
    path: 'admin',
    component: MainAdminPageComponent,
    children: [
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        path: 'customers',
        component: ManageUsersComponent
      },
      {
        path: 'dashboard',
        component: AdminDashboardComponent
      },
      {
        path: 'profile',
        component: ProfileComponent
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
