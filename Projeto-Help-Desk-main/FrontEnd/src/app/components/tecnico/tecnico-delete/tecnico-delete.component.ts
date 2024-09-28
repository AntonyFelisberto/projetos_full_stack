import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Technical } from 'src/app/models/Technicals';
import { TecnicoService } from 'src/app/services/tecnicoService/tecnico.service';

@Component({
  selector: 'app-tecnico-delete',
  templateUrl: './tecnico-delete.component.html',
  styleUrls: ['./tecnico-delete.component.css']
})
export class TecnicoDeleteComponent implements OnInit {

  technical:Technical ={
    name: '',
    cpf: '',
    email: '',
    password: '',
    profile: [],
    dateCreation: ''
  }

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

  delete():void{
      this.technicalService.delete(this.technical.personId).subscribe(
        () => {
          this.toast.success('Tecnico deletado com sucesso','Deletado com sucesso')
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

}
