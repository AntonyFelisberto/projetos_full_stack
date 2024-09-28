import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from '@angular/router';
import { Call } from 'src/app/models/Call';
import { Client } from 'src/app/models/Client';
import { Technical } from 'src/app/models/Technicals';
import { CallService } from 'src/app/services/callService/call-service';

@Component({
  selector: 'app-chamado-read',
  templateUrl: './chamado-read.component.html',
  styleUrls: ['./chamado-read.component.css']
})
export class ChamadoReadComponent implements OnInit {

  chamado:Call = {
    priority: '',
    status: '',
    title: '',
    observations: '',
    tecnico: '',
    cliente: '',
    nameTechnical: '',
    nameClient: ''
  }

  constructor(private callService:CallService,
              private activatedRouter:ActivatedRoute) { }

  ngOnInit(): void {
    this.chamado.id = this.activatedRouter.snapshot.paramMap.get('id');
    this.findChamado();
  }

  findChamado():void{
    this.callService.findById(this.chamado.id).subscribe(resposta =>{
      this.chamado = resposta
    })
  }

  retornaStatus(status:any):string{
    if(status == "0"){
      return "ABERTO";
    }else if(status == "1"){
      return "EM ANDAMENTO";
    }else{
      return "ENCERRADO";
    }
  }

  retornaPrioridade(prioridade:any):string{
    if(prioridade == "0"){
      return "BAIXA";
    }else if(prioridade == "1"){
      return "MÉDIA";
    }else{
      return "ALTA";
    }
  }

}
