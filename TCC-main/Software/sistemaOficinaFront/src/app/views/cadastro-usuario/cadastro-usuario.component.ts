import { UsuarioService } from './../../services/usuarioService/usuario.service';
import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AlertsService } from 'src/app/services/alert/alerts.service';
import {FormControl, FormGroupDirective} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {MessageService} from 'primeng/api';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-cadastro-usuario',
  templateUrl: './cadastro-usuario.component.html',
  styleUrls: ['./cadastro-usuario.component.scss'],
  providers: [MessageService]
})
export class CadastroUsuarioComponent implements OnInit {

  constructor(private usuarioService: UsuarioService,
              private alerts:AlertsService,
              private messageService: MessageService
              ) {}

  ngOnInit(): void {}

  public adicionarUsuario(dadosUsuario: NgForm) {
    this.usuarioService.adicionarUsuarios(dadosUsuario.value).subscribe(
      () => {
        this.alerts.success("Usuario cadastrado com sucesso", "Sucesso");
        document.getElementById("myCheck").click();
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
