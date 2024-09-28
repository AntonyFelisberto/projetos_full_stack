import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Orcamento } from 'src/app/models/Orcamento/Orcamento';
import { OrcamentoDTO } from 'src/app/models/Orcamento/OrcamentoDTO';
import { dados } from 'src/app/models/Usuarios/dados';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OrcamentoService {

  private linkParaConexaoComBack = environment.linkDeConexaoComBackEnd;

  constructor(private httpClient:HttpClient) { }

  public trazerOrcamento(): Observable<OrcamentoDTO[]>{
    return this.httpClient.get<OrcamentoDTO[]>(`${this.linkParaConexaoComBack}/orcamento/all`)
  }

  public trazerIdOrcamento(){
    return this.httpClient.get<number>(`${this.linkParaConexaoComBack}/orcamento/id`)
  }

  public recuperarArquivoPdf(idOrcamento:number){
    return this.httpClient.get(`${this.linkParaConexaoComBack}/orcamento/find/${idOrcamento}`, { responseType:'blob' }).toPromise();
  }

  public reenviarOrcamento(idOrcamento:number){
    return this.httpClient.post(`${this.linkParaConexaoComBack}/orcamento/reenviar/${idOrcamento}`,null)
  }

  public enviarEmailCliente(orcamentoEnvio:Orcamento){
    return this.httpClient.post<string>(`${this.linkParaConexaoComBack}/orcamento/add/${dados.id}`,orcamentoEnvio).toPromise();
  }

  getPdfReport(idOrcamento:number) {
    return this.httpClient.get(`${this.linkParaConexaoComBack}/orcamento/find/${idOrcamento}`, { responseType: 'blob', observe: 'response'}).pipe(
      map((res: any) => {
        return new Blob([res.body], { type: 'application/pdf' });
      })
    );
  }

}
