import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http'

import { Empregado } from './empregado';
import { environment } from 'src/environments/environment';



@Injectable({
    providedIn: 'root'
})
export class EmpregadoService {
    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient){}

    public getEmpregados(): Observable<Empregado[]>{
        return this.http.get<Empregado[]>(`${this.apiServerUrl}/funcionarios/all`);
    }

    public addEmpregados(empregado: Empregado): Observable<Empregado>{
        return this.http.post<Empregado>(`${this.apiServerUrl}/funcionarios/add`,empregado);
    }
    
    public updateEmpregados(empregado: Empregado): Observable<Empregado>{
        return this.http.put<Empregado>(`${this.apiServerUrl}/funcionarios/update`,empregado);
    }

    public deleteEmpregados(empregadoId: number): Observable<void>{
        return this.http.delete<void>(`${this.apiServerUrl}/funcionarios/delete/${empregadoId}`);
    }
}