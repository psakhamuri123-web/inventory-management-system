import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector:'app-root',
  standalone:true,
  imports:[RouterOutlet, RouterLink],
  template:`
  <nav class="nav">
    <span class="brand">Inventory Manager</span>
    <a routerLink="/">Dashboard</a>
    <a routerLink="/products">Products</a>
    <a routerLink="/inventory">Inventory</a>
    <a routerLink="/login">Login</a>
  </nav>
  <router-outlet></router-outlet>
  `
})
export class AppComponent {}
