import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from './api.service';

@Component({
  standalone:true, imports:[CommonModule,FormsModule],
  template:`
  <div class="container">
    <div class="toolbar"><h1>Products</h1><input style="max-width:300px" placeholder="Search SKU or name" [(ngModel)]="search" (input)="load()"></div>
    <div class="card">
      <table>
        <thead><tr><th>SKU</th><th>Name</th><th>Category</th><th>Supplier</th><th>Price</th><th>Qty</th><th>Status</th></tr></thead>
        <tbody>
          <tr *ngFor="let p of products">
            <td>{{p.sku}}</td><td>{{p.name}}</td><td>{{p.category?.name}}</td><td>{{p.supplier?.name}}</td>
            <td>{{p.price | currency}}</td><td>{{p.quantity}}</td>
            <td><span *ngIf="p.quantity<=p.reorderLevel" class="badge">Low stock</span><span *ngIf="p.quantity>p.reorderLevel">OK</span></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>`
})
export class ProductsComponent implements OnInit {
  private api=inject(ApiService); products:any[]=[]; search='';
  ngOnInit(){this.load();}
  load(){this.api.products(this.search).subscribe(x=>this.products=x);}
}
