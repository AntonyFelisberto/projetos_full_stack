import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Technical } from 'src/app/models/Technicals';
import { TecnicoService } from 'src/app/services/tecnicoService/tecnico.service';

@Component({
  selector: 'app-tecnico-update',
  templateUrl: './tecnico-update.component.html',
  styleUrls: ['./tecnico-update.component.css']
})
export class TecnicoUpdateComponent implements OnInit {

  technical:Technical ={
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

  constructor(private technicalService:TecnicoService,
              private toast:ToastrService,
              private router:Router,
              private activatedRouter:ActivatedRoute) { }

  ngOnInit(): void {
    this.technical.personId = this.activatedRouter.snapshot.paramMap.get('id');
    this.findById();
  }

  findById():void{
    this.technicalService.findById(this.technical.personId).subscribe(
      response =>{
        response.profile = [];
        this.technical = response;
      }
    )
  }

  update():void{
      this.technicalService.update(this.technical).subscribe(
        () => {
          this.toast.success('Tecnico atualizado com sucesso','Atualizado com sucesso')
          this.router.navigate(['technical'])
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
    if(this.technical.profile.includes(perfil)){
      this.technical.profile.splice(this.technical.profile.indexOf(perfil),1)
    }else{
      this.technical.profile.push(perfil)
    }
  }

  validaCampos():boolean{
    return this.nome.valid && this.cpf.valid && this.email.valid && this.senha.valid
  }

}
