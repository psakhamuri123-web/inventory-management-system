import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from './api.service';

@Component({
  standalone:true, imports:[FormsModule],
  template:`
  <div class="container">
    <div class="card" style="max-width:420px;margin:60px auto">
      <h2>Sign in</h2>
      <p>Demo account: admin / admin123</p>
      <div class="form-row"><label>Username</label><input [(ngModel)]="username"></div>
      <div class="form-row"><label>Password</label><input type="password" [(ngModel)]="password"></div>
      <div class="error" *ngIf="error">{{error}}</div>
      <button class="btn" (click)="login()">Login</button>
    </div>
  </div>`
})
export class LoginComponent {
  private api=inject(ApiService); private router=inject(Router);
  username='admin'; password='admin123'; error='';
  login(){
    this.api.login({username:this.username,password:this.password}).subscribe({
      next:r=>{localStorage.setItem('token',r.token);this.router.navigateByUrl('/');},
      error:()=>this.error='Invalid username or password'
    });
  }
}
