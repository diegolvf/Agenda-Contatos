import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contato } from '../models/contato-model';

@Injectable({
  providedIn: 'root'
})
export class ContatoService {

  private baseUrl = 'http://localhost:8080/contatos';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Contato[]> {
    return this.http.get<Contato[]>(this.baseUrl);
  }

  findById(id: number): Observable<Contato> {
    return this.http.get<Contato>(`${this.baseUrl}/${id}`);
  }

  create(data: any): Observable<Contato> {
    return this.http.post<Contato>(this.baseUrl, data);
  }

  update(id: number, data: any): Observable<Contato> {
    return this.http.put<Contato>(`${this.baseUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  search(termo: string): Observable<Contato[]> {
    return this.http.get<Contato[]>(`${this.baseUrl}/search?termo=${termo}`);
  }
}

