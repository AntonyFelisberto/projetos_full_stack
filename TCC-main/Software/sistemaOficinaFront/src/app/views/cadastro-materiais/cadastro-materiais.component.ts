import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AlertsService } from 'src/app/services/alert/alerts.service';
import { MateriaisService } from 'src/app/services/materiaisService/cadastrar-materiais.service';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-cadastro-materiais',
  templateUrl: './cadastro-materiais.component.html',
  styleUrls: ['./cadastro-materiais.component.scss'],
  providers: [MessageService]
})
export class CadastroMateriaisComponent implements OnInit {

  constructor(private materialService:MateriaisService,
              private alerta: AlertsService,
              private messageService: MessageService) { }

  ngOnInit(): void {}

  public adicionarMaterial(material:NgForm){
    this.materialService.adicionarMateriais(material.value).subscribe(
      () => {
        this.alerta.success("Cadastro efetuado com sucesso","Sucesso");
        document.getElementById("myCheck").click();
      },exception=>{
        if(exception.error.error){
          exception.error.error.array.forEach(element => {
            this.messageService.add({severity:'error', summary: element.fieldName, detail: element.message});
          });
        }else{
          this.messageService.add({severity:'error', summary: 'Erro', detail: exception.error.message});
        }
      }
    )
  }

}
