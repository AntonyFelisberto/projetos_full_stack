import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Client } from 'src/app/models/Client';
import { ClientService } from 'src/app/services/clientService/client-service';

@Component({
  selector: 'app-client-create',
  templateUrl: './client-create.component.html',
  styleUrls: ['./client-create.component.css']
})
export class ClientCreateComponent implements OnInit {

  client:Client ={
    name: '',
    cpf: '',
    email: '',
    password: '',
    profile: [],
    dateCreation: ''
  }

  nome:FormControl = new FormControl(null, Validators.minLength(3))
  cpf:FormControl = new FormControl(null, [Validators.required, Validators.minLength(11),Validators.maxLength(14)])
  email:FormControl = new FormControl(null, Validators.email)
  senha:FormControl = new FormControl(null, Validators.minLength(3))

  constructor(private clientService:ClientService,
              private toast:ToastrService,
              private router:Router) { }

  ngOnInit(): void {
  }



  create():void{
      this.clientService.create(this.client).subscribe(
        () => {
          this.toast.success('Cliente cadastrado com sucesso','Cadastro com sucesso')
          this.router.navigate(['client'])
        },exception =>{
          if(exception.error.errors){
            exception.error.errors.forEach(erros => {
              this.toast.error(erros.message);
            });
          }else{
            this.toast.error(exception.error.message);
          }
        }
      )
  }
  
  addPerfil(perfil:any):void{
    if(this.client.profile.includes(perfil)){
      this.client.profile.splice(this.client.profile.indexOf(perfil),1)
    }else{
      this.client.profile.push(perfil)
    }
  }

  validaCampos():boolean{
    return this.nome.valid && this.cpf.valid && this.email.valid && this.senha.valid
  }

}
