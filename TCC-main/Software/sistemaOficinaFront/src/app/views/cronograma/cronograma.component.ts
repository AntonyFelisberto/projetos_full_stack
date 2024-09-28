import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Cronograma } from 'src/app/models/Cronograma/Cronograma';
import { AlertsService } from 'src/app/services/alert/alerts.service';
import { CronogramaService } from 'src/app/services/CronogramaService/cronograma-service.service';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-cronograma',
  templateUrl: './cronograma.component.html',
  styleUrls: ['./cronograma.component.scss' ],
  providers: [MessageService]
})


export class CronogramaComponent implements OnInit {

  public dadosCronogramaUsuario:Cronograma[];
  public dadosValidar:Cronograma;

  constructor(private alerta:AlertsService,
              private cronogramaService:CronogramaService,
              private messageService: MessageService) { }

  dates:Date;

  minDate: Date;

  ngOnInit() {
    this.buscarDadosCronograma()
    let hoje = new Date();
    this.minDate = new Date();
    var minutos = 10;
    this.minDate.setMinutes(hoje.getMinutes() + minutos)
  }

  filtrar(){

    const dadosFiltrados:Cronograma[] = [];
    var dataSelecionada = this.dates.toISOString().split("T",1)
    for(const filtrar of this.dadosCronogramaUsuario){

      let dataArray = new Date(filtrar.dataInicio)
      let dataArrayComparacao = dataArray.toISOString().split("T",1)

      if(dataArrayComparacao.indexOf(dataSelecionada[0]) !== -1){
        dadosFiltrados.push(filtrar)
      }
    }

    this.dadosCronogramaUsuario = dadosFiltrados

    if(this.dadosCronogramaUsuario.length === 0){
      this.buscarDadosCronograma();
    }

  }

  inserirDadosCronograma(dadosNovoCronograma:NgForm){
    this.dadosValidar =  dadosNovoCronograma.value;
    var dataInicial = this.dadosValidar.dataInicio;
    var dataFinal = this.dadosValidar.dataFim;
    if (dataInicial >= dataFinal) {
      this.messageService.add({severity:'error', summary: 'ERRO', detail:'data inicial maior ou igual a data final, fazer alteraçao'});
    } else {
      this.cronogramaService.inserirDados(dadosNovoCronograma.value).subscribe(
        () => {
          this.alerta.success("Dados Inseridos com sucesso","Sucesso");
          this.buscarDadosCronograma();
          document.getElementById("myCheck").click();
        },exception=>{
          if(exception.error.error){
            exception.error.error.forEach(element => {
              this.messageService.add({severity:'error', summary: element.fieldName, detail: element.message});
            });
          }else{
            this.messageService.add({severity:'error', summary: 'Error', detail: exception.error.message});
          }
        }
      )
    }
  }

  private buscarDadosCronograma(){
    this.cronogramaService.buscarDados().subscribe(
      (response:Cronograma[]) =>{
        this.dadosCronogramaUsuario = response;
      }
    )
  }

  atualizarDadosCronograma(dadosAtualizadosCronograma:Cronograma){

    if (dadosAtualizadosCronograma.dataInicio >= dadosAtualizadosCronograma.dataFim) {
      this.messageService.add({severity:'error', summary: 'ERRO', detail:'data inicial maior ou igual a data final, fazer alteraçao'});
    } else {
      this.cronogramaService.atualizarDados(dadosAtualizadosCronograma).subscribe(
        () => {
          this.alerta.success("Dados atualizados com sucesso","Sucesso");
          this.buscarDadosCronograma();
        },exception=>{
          if(exception.error.error){
            exception.error.error.forEach(element => {
              this.messageService.add({severity:'error', summary: element.fieldName, detail: element.message});
            });
          }else{
            this.messageService.add({severity:'error', summary: 'Error', detail: exception.error.message});
          }
        }
      )
    }
  }

  deletarDadosCronograma(idCronograma:number){
    this.cronogramaService.deletarDados(idCronograma).subscribe(
      ()=>{
        this.alerta.success("Dados removidos com sucesso","Sucesso")
        this.buscarDadosCronograma();
      },exception=>{
        if(exception.error.error){
          exception.error.error.forEach(element => {
            this.messageService.add({severity:'error', summary: element.fieldName, detail: element.message});
          });
        }else{
          this.messageService.add({severity:'error', summary: 'Error', detail: exception.error.message});
        }
      }
    )
  }

}
