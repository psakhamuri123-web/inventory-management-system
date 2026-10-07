import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from './api.service';

@Component({
  standalone:true, imports:[CommonModule],
  template:`
  <div class="container">
    <div class="toolbar"><div><h1>Dashboard</h1><p>Inventory overview</p></div></div>
    <div class="grid grid-4">
      <div class="card"><div>Products</div><div class="stat">{{s.products}}</div></div>
      <div class="card"><div>Categories</div><div class="stat">{{s.categories}}</div></div>
      <div class="card"><div>Suppliers</div><div class="stat">{{s.suppliers}}</div></div>
      <div class="card"><div>Low Stock</div><div class="stat">{{s.lowStock}}</div></div>
    </div>
    <div class="card" style="margin-top:18px"><h3>Total Units in Stock</h3><div class="stat">{{s.totalUnits}}</div></div>
  </div>`
})
export class DashboardComponent implements OnInit {
  private api=inject(ApiService); s:any={products:0,categories:0,suppliers:0,totalUnits:0,lowStock:0};
  ngOnInit(){this.api.dashboard().subscribe(x=>this.s=x);}
}
