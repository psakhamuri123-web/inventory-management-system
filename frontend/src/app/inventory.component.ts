import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from './api.service';

@Component({
  standalone:true, imports:[CommonModule,FormsModule],
  template:`
  <div class="container">
    <div class="grid grid-2">
      <div class="card">
        <h2>Stock Transaction</h2>
        <div class="form-row"><label>Product</label><select [(ngModel)]="form.productId"><option *ngFor="let p of products" [value]="p.id">{{p.sku}} - {{p.name}}</option></select></div>
        <div class="form-row"><label>Type</label><select [(ngModel)]="form.type"><option value="STOCK_IN">Stock In</option><option value="STOCK_OUT">Stock Out</option><option value="ADJUSTMENT">Adjustment</option></select></div>
        <div class="form-row"><label>Quantity</label><input type="number" [(ngModel)]="form.quantity"></div>
        <div class="form-row"><label>Reference</label><input [(ngModel)]="form.reference"></div>
        <div class="form-row"><label>Notes</label><input [(ngModel)]="form.notes"></div>
        <div class="error">{{message}}</div>
        <button class="btn" (click)="save()">Save Transaction</button>
      </div>
      <div class="card">
        <h2>Recent Transactions</h2>
        <table><thead><tr><th>Product</th><th>Type</th><th>Qty</th><th>Date</th></tr></thead>
        <tbody><tr *ngFor="let t of tx"><td>{{t.product?.name}}</td><td>{{t.type}}</td><td>{{t.quantity}}</td><td>{{t.transactionDate | date:'short'}}</td></tr></tbody></table>
      </div>
    </div>
  </div>`
})
export class InventoryComponent implements OnInit {
  private api=inject(ApiService); products:any[]=[]; tx:any[]=[]; message='';
  form:any={productId:null,type:'STOCK_IN',quantity:1,reference:'',notes:''};
  ngOnInit(){this.load();}
  load(){this.api.products().subscribe(x=>{this.products=x;if(!this.form.productId&&x.length)this.form.productId=x[0].id;});this.api.transactions().subscribe(x=>this.tx=x);}
  save(){this.message='';this.api.stock({...this.form,productId:Number(this.form.productId),quantity:Number(this.form.quantity)}).subscribe({next:()=>{this.message='Transaction saved.';this.load();},error:e=>this.message=e.error?.message||'Unable to save transaction'});}
}
