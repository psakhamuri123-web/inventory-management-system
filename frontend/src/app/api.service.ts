import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({providedIn:'root'})
export class ApiService {
  private http = inject(HttpClient);
  private base = 'http://localhost:8080/api';

  login(data:any){ return this.http.post<any>(`${this.base}/auth/login`, data); }
  dashboard(){ return this.http.get<any>(`${this.base}/dashboard/summary`); }
  products(search=''){ return this.http.get<any[]>(`${this.base}/products`, {params: search ? {search} : {}}); }
  lowStock(){ return this.http.get<any[]>(`${this.base}/products/low-stock`); }
  categories(){ return this.http.get<any[]>(`${this.base}/categories`); }
  suppliers(){ return this.http.get<any[]>(`${this.base}/suppliers`); }
  createProduct(data:any){ return this.http.post<any>(`${this.base}/products`, data); }
  updateProduct(id:number,data:any){ return this.http.put<any>(`${this.base}/products/${id}`, data); }
  deleteProduct(id:number){ return this.http.delete(`${this.base}/products/${id}`); }
  transactions(){ return this.http.get<any[]>(`${this.base}/inventory/transactions`); }
  stock(data:any){ return this.http.post<any>(`${this.base}/inventory/transaction`, data); }
}
