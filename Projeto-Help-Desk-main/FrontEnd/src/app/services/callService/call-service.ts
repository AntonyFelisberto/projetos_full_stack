import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { API_CONFIG } from 'src/app/config/api.config';
import { Call } from 'src/app/models/Call';

@Injectable({
  providedIn: 'root'
})
export class CallService {

  constructor(private httpClient:HttpClient) { }

  findAll():Observable<Call[]>{
    return this.httpClient.get<Call[]>(`${API_CONFIG.baseUrl}/calls`)
  }

  findById(id:any):Observable<Call>{
    return this.httpClient.get<Call>(`${API_CONFIG.baseUrl}/calls/${id}`)
  }

  create(chamado:Call):Observable<Call>{
    return this.httpClient.post<Call>(`${API_CONFIG.baseUrl}/calls`,chamado)
  }

  update(chamado:Call):Observable<Call>{
    return this.httpClient.put<Call>(`${API_CONFIG.baseUrl}/calls/${chamado.id}`,chamado)
  }


}
