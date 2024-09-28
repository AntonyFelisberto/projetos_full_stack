import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cronograma } from 'src/app/models/Cronograma/Cronograma';
import { dados } from 'src/app/models/Usuarios/dados';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CronogramaService {

  private linkParaConexaoComBackEnd = environment.linkDeConexaoComBackEnd;

  constructor(private httpCliente:HttpClient) { }

  inserirDados(dadosCronograma:Cronograma){
    return this.httpCliente.post<Cronograma>(`${this.linkParaConexaoComBackEnd}/cronograma/add/${dados.id}`,dadosCronograma)
  }

  buscarDados():Observable<Cronograma[]>{
    return this.httpCliente.get<Cronograma[]>(`${this.linkParaConexaoComBackEnd}/cronograma/find/${dados.id}`)
  }

  deletarDados(idCronograma:number){
    return this.httpCliente.delete(`${this.linkParaConexaoComBackEnd}/cronograma/delete/${idCronograma}`)
  }

  atualizarDados(dadosCronograma:Cronograma){
    return this.httpCliente.put<Cronograma>(`${this.linkParaConexaoComBackEnd}/cronograma/update`,dadosCronograma)
  }

}
