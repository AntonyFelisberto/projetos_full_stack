import { Component, OnInit } from '@angular/core';
import { AlertsService } from 'src/app/services/alert/alerts.service';
import {Router} from "@angular/router"
import { UsuarioService } from 'src/app/services/usuarioService/usuario.service';
import { NgForm } from '@angular/forms';
import { dados } from 'src/app/models/Usuarios/dados';
import { UsuarioDTO } from 'src/app/models/Usuarios/UsuarioDTO';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private usuario:UsuarioDTO;
  usuarioValido = {
    nome:'',
    senha:''
  }
  numeroId:number
  
  constructor(private alerta:AlertsService,
              private router: Router,
              private usuarioService: UsuarioService) {}

  ngOnInit(): void {}

  public doLogin(usuario: NgForm) {
    this.usuarioService.logar(usuario.value).subscribe(
      response =>{
          this.usuarioService.sucessFullLogin(response.headers.get('Authorization').substring(7))
          this.usuario = {
            'nome':this.usuarioValido.nome,
            'senha':this.usuarioValido.senha
          }
          this.usuarioService.trazerIdUsuario(this.usuario).subscribe(
            (id:number)=>{
              this.numeroId = id
              dados.id = this.numeroId
            }
          )
          this.router.navigate(['nav'])
      },()=>{
        this.alerta.error("ERRO NA VALIDAÇÃO, VERIFIQUE TODOS OS CAMPOS","ERRO")
      }
    )
  }

}