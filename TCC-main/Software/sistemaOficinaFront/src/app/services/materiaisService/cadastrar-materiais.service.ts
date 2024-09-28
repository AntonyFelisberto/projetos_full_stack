import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { MateriaisUsuario } from '../../models/Materiais/MateriaisUsuario';

@Injectable({
  providedIn: 'root'
})
export class MateriaisService {

  private linkParaConexao = environment.linkDeConexaoComBackEnd;

  constructor(private httpClient: HttpClient) { }

  public adicionarMateriais(materiais: MateriaisUsuario){
    return this.httpClient.post<MateriaisUsuario>(`${this.linkParaConexao}/materiais/add`,materiais);
  }

  public removerMateriais(id:number){
    return this.httpClient.delete<void>(`${this.linkParaConexao}/materiais/delete/${id}`);
  }

  public trazerMateriais(): Observable<MateriaisUsuario[]>{
    return this.httpClient.get<MateriaisUsuario[]>(`${this.linkParaConexao}/materiais/all`)
  }

  public atualizarMateriais(materiais:MateriaisUsuario){
    return this.httpClient.put<MateriaisUsuario>(`${this.linkParaConexao}/materiais/update`,materiais)
  }

}
