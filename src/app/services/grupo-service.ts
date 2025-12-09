import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Grupo } from '../models/grupo-model';

@Injectable({
  providedIn: 'root'
})
export class GrupoService {

  private baseUrl = 'http://localhost:8080/grupos';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Grupo[]> {
    return this.http.get<Grupo[]>(this.baseUrl);
  }

  findById(id: number): Observable<Grupo> {
    return this.http.get<Grupo>(`${this.baseUrl}/${id}`);
  }

  create(data: Grupo): Observable<Grupo> {
    return this.http.post<Grupo>(this.baseUrl, data);
  }

  update(id: number, data: Grupo): Observable<Grupo> {
    return this.http.put<Grupo>(`${this.baseUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
