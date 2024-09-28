
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Toast, ToastrService } from 'ngx-toastr';
import { Call } from 'src/app/models/Call';
import { Client } from 'src/app/models/Client';
import { Technical } from 'src/app/models/Technicals';
import { CallService } from 'src/app/services/callService/call-service';
import { ClientService } from 'src/app/services/clientService/client-service';
import { TecnicoService } from 'src/app/services/tecnicoService/tecnico.service';

@Component({
  selector: 'app-chamado-create',
  templateUrl: './chamado-create.component.html',
  styleUrls: ['./chamado-create.component.css']
})
export class ChamadoCreateComponent implements OnInit {

  prioridade:FormControl = new FormControl(null, [Validators.required])
  status:FormControl = new FormControl(null, [Validators.required])
  titulo:FormControl = new FormControl(null, [Validators.required])
  descricao:FormControl = new FormControl(null, [Validators.required])
  tecnico:FormControl = new FormControl(null, [Validators.required])
  cliente:FormControl = new FormControl(null, [Validators.required])

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
  clientes:Client[]=[]
  tecnicos:Technical[]=[]

  constructor(private callService:CallService,
              private clientService:ClientService,
              private tecnicoService:TecnicoService,
              private toast:ToastrService,
              private router:Router) { }

  ngOnInit(): void {
    this.findAllClients();
    this.findAllTecnicos();
  }

  findAllClients():void{
    this.clientService.findAll().subscribe(resposta =>{
      this.clientes = resposta
    })
  }

  findAllTecnicos():void{
    this.tecnicoService.findAll().subscribe(resposta =>{
      this.tecnicos = resposta
    })
  }

  create():void{
    this.callService.create(this.chamado).subscribe(resposta=>{
      this.toast.success('Chamado criado','Sucesso')
      this.router.navigate(['chamados'])
    },exception =>{
      if(exception.error.errors){
        exception.error.errors.forEach(erros => {
          this.toast.error(erros.message);
        });
      }else{
        console.log(exception.error.message)
        this.toast.error(exception.error.message);
      }
    })
   }

  validaCampos():boolean{
    return this.prioridade.valid &&
        this.status.valid &&
        this.titulo.valid &&
        this.descricao.valid &&
        this.tecnico.valid &&
        this.cliente.valid
  }

}
