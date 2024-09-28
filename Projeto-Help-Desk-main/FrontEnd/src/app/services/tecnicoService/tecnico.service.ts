import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from 'src/app/config/api.config';
import { Technical } from 'src/app/models/Technicals';

@Injectable({
  providedIn: 'root'
})
export class TecnicoService {

  constructor(private httpCliente:HttpClient) { }


  findById(id:any): Observable<Technical>{
    return this.httpCliente.get<Technical>(`${API_CONFIG.baseUrl}/technical/${id}`)
  }

  findAll():Observable<Technical[]>{
    return this.httpCliente.get<Technical[]>(`${API_CONFIG.baseUrl}/technical`)
  }

  update(technical:Technical):Observable<Technical>{
    return this.httpCliente.put<Technical>(`${API_CONFIG.baseUrl}/technical/${technical.personId}`,technical)
  }

  create(technical:Technical): Observable<Technical>{
    return this.httpCliente.post<Technical>(`${API_CONFIG.baseUrl}/technical`,technical)
  }

  delete(id:any): Observable<Technical>{
    return this.httpCliente.delete<Technical>(`${API_CONFIG.baseUrl}/technical/${id}`)
  }

}
