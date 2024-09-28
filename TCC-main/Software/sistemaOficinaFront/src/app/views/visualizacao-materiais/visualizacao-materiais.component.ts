import { Component, OnInit } from '@angular/core';
import { MateriaisUsuario } from 'src/app/models/Materiais/MateriaisUsuario';
import { AlertsService } from 'src/app/services/alert/alerts.service';
import { MateriaisService } from 'src/app/services/materiaisService/cadastrar-materiais.service';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-visualizacao-materiais',
  templateUrl: './visualizacao-materiais.component.html',
  styleUrls: ['./visualizacao-materiais.component.scss'],
  providers: [MessageService]
})
export class VisualizacaoMateriaisComponent implements OnInit {

  public materiais!:MateriaisUsuario[];

  constructor(private materiaisService:MateriaisService,
              private alerts:AlertsService,
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.trazerMateriais();
  }

  public filtrar(material:string){

    const buscaResultado:MateriaisUsuario[] = [];

    for(const listaMateriais of this.materiais){
      if(listaMateriais.codProduto === Number(material) ||
        listaMateriais.descriminizacao.toLowerCase().indexOf(material.toLowerCase()) !== -1 ||
        listaMateriais.precoUnidade === Number(material) ||
        listaMateriais.quantidade === Number(material) ||
        listaMateriais.unidade === Number(material)){
        buscaResultado.push(listaMateriais)
      }
    }

    this.materiais = buscaResultado;

    if(buscaResultado.length === 0 || !material){
      this.trazerMateriais();
    }

  }

  public trazerMateriais(){
    this.materiaisService.trazerMateriais().subscribe(
      (material:MateriaisUsuario[]) =>{
        this.materiais = material
      }
    )
  }

  public deletarMateriaisPorId(id:number){
    this.materiaisService.removerMateriais(id).subscribe(
      () => {
        this.alerts.success("Material deletado Com Sucesso","Sucesso")
        this.trazerMateriais();
      },exception=>{
        if(exception.error.error){
          exception.error.error.forEach(element => {
            this.messageService.add({severity:'error', summary: element.fieldName, detail: element.message});
          });
        }else{
          this.messageService.add({severity:'error', summary: 'Erro', detail: exception.error.message});
        }
      }
    )
  }

  public atualizarMateriais(materiais:MateriaisUsuario){
    this.materiaisService.atualizarMateriais(materiais).subscribe(
      () => {
        this.alerts.success("Material atualizado Com Sucesso","Sucesso")
        this.trazerMateriais();
      },exception=>{
        if(exception.error.error){
          exception.error.error.forEach(element => {
            this.messageService.add({severity:'error', summary: element.fieldName, detail: element.message});
          });
        }else{
          this.messageService.add({severity:'error', summary: 'Erro', detail: exception.error.message});
        }
      }
    )
  }

}