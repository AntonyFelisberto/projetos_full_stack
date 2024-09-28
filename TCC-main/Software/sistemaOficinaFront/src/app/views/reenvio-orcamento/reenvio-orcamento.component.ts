import { Component, OnInit } from '@angular/core';
import { OrcamentoDTO } from 'src/app/models/Orcamento/OrcamentoDTO';
import { OrcamentoService } from 'src/app/services/Orcamento/orcamento.service';
import {MessageService} from 'primeng/api';


@Component({
  selector: 'app-reenvio-orcamento',
  templateUrl: './reenvio-orcamento.component.html',
  styleUrls: ['./reenvio-orcamento.component.scss'],
  providers: [MessageService]
})
export class ReenvioOrcamentoComponent implements OnInit {

  public orcamentos!:OrcamentoDTO[];

  constructor (private orcamentoService:OrcamentoService,
               private messageService: MessageService){}

  ngOnInit(): void {
    this.trazerOrcamentos();
  }

  public pesquisar(filtro:string){

    const orcamentoPesquisa:OrcamentoDTO[] = [];

    for(const listaOrcamentos of this.orcamentos){
      if( listaOrcamentos.email.toLowerCase().indexOf(filtro.toLowerCase()) !== -1 ||
          listaOrcamentos.nome.toLowerCase().indexOf(filtro.toLowerCase()) !== -1 ||
          listaOrcamentos.telefone.toLowerCase().indexOf(filtro.toLowerCase()) !== -1 ||
          listaOrcamentos.veiculo.toLowerCase().indexOf(filtro.toLowerCase()) !== -1 ||
          listaOrcamentos.endereco.toLowerCase().indexOf(filtro.toLowerCase()) !== -1 ||
          listaOrcamentos.celular.toLowerCase().indexOf(filtro.toLowerCase()) !== -1 ||
          listaOrcamentos.idNota === Number(filtro)
        ){
          orcamentoPesquisa.push(listaOrcamentos)
      }
    }

    this.orcamentos = orcamentoPesquisa;

    if(orcamentoPesquisa.length === 0 || !filtro){
      this.trazerOrcamentos();
    }
  }

  public trazerOrcamentos(){
    this.orcamentoService.trazerOrcamento().subscribe(
      (response:OrcamentoDTO[]) => {
        this.orcamentos = response;
      }
    )
  }

  public reenviarOrcamento(idNota:number){
    this.orcamentoService.reenviarOrcamento(idNota).subscribe();
    this.messageService.add({severity:'info', summary: "", detail: "thread para envio do email realizado, Confirme com o cliente o recebimento do Orçamento"});
  }
  
}