import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from 'src/app/config/api.config';
import { Client } from 'src/app/models/Client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private httpCliente:HttpClient) { }


  findById(id:any): Observable<Client>{
    return this.httpCliente.get<Client>(`${API_CONFIG.baseUrl}/client/${id}`)
  }

  findAll():Observable<Client[]>{
    return this.httpCliente.get<Client[]>(`${API_CONFIG.baseUrl}/client`)
  }

  update(Client:Client):Observable<Client>{
    return this.httpCliente.put<Client>(`${API_CONFIG.baseUrl}/client/${Client.personId}`,Client)
  }

  create(Client:Client): Observable<Client>{
    return this.httpCliente.post<Client>(`${API_CONFIG.baseUrl}/client`,Client)
  }

  delete(id:any): Observable<Client>{
    return this.httpCliente.delete<Client>(`${API_CONFIG.baseUrl}/client/${id}`)
  }

}
