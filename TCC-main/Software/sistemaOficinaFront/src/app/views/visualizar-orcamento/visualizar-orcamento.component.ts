import { Component, OnInit } from '@angular/core';
import { OrcamentoService } from 'src/app/services/Orcamento/orcamento.service';
import { OrcamentoDTO } from 'src/app/models/Orcamento/OrcamentoDTO';
import { saveAs } from 'file-saver'
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-visualizar-orcamento',
  templateUrl: './visualizar-orcamento.component.html',
  styleUrls: ['./visualizar-orcamento.component.scss'],
  providers: [MessageService]
})
export class VisualizarOrcamentoComponent implements OnInit {


  public orcamento!:OrcamentoDTO[];
  public BlobUri:string

  constructor (private orcamentoService:OrcamentoService,
               private messageService: MessageService){}

  ngOnInit(): void {
    this.trazerOrcamentos();
  }

  public trazerOrcamentos(){
    this.orcamentoService.trazerOrcamento().subscribe(
      (response:OrcamentoDTO[]) => {
        this.orcamento = response;
      }
    )
  }

  filtrarDados(filtro:string){

    const dadosOrcamentos:OrcamentoDTO[] = []

    for(const filtragem of this.orcamento){
      if(filtragem.idNota === Number(filtro) ||
      filtragem.nome.toLowerCase().indexOf(filtro.toLowerCase()) !== -1 ||
      filtragem.celular.toLowerCase().indexOf(filtro.toLowerCase()) !== -1 ||
      filtragem.email.toLowerCase().indexOf(filtro.toLowerCase()) !== -1 ||
      filtragem.endereco.toLowerCase().indexOf(filtro.toLowerCase()) !== -1 ||
      filtragem.telefone.toLowerCase().indexOf(filtro.toLowerCase()) !== -1 ||
      filtragem.veiculo.toLowerCase().indexOf(filtro.toLowerCase()) !== -1){
        dadosOrcamentos.push(filtragem)
      }
    }

    this.orcamento = dadosOrcamentos

    if(dadosOrcamentos.length === 0 || !filtro){
      this.trazerOrcamentos();
    }
  }

  exibirArquivoPdf(idNota:number){
    this.orcamentoService.recuperarArquivoPdf(idNota).then(result => {
      const fileURL = URL.createObjectURL(result);
      window.open(fileURL, '_blank');
    },exception=>{
      if(exception.error.error){
        exception.error.error.forEach(element => {
          this.messageService.add({severity:'error', summary: element.fieldName, detail: element.message});
        });
      }else{
        this.messageService.add({severity:'error', summary: 'Erro', detail: exception.error.message});
      }
    })
  }

  baixarPdf(idNota:number){
    this.orcamentoService.recuperarArquivoPdf(idNota).then(blob=> {
       saveAs(blob, 'Orçamento.pdf');
    },exception=>{
      if(exception.error.error){
        exception.error.error.forEach(element => {
          this.messageService.add({severity:'error', summary: element.fieldName, detail: element.message});
        });
      }else{
        this.messageService.add({severity:'error', summary: 'Erro', detail: exception.error.message});
      }
    })
  }

}
