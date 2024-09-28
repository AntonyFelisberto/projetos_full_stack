import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Client } from 'src/app/models/Client';
import { ClientService } from 'src/app/services/clientService/client-service';


@Component({
  selector: 'app-client-delete',
  templateUrl: './client-delete.component.html',
  styleUrls: ['./client-delete.component.css']
})
export class ClientDeleteComponent implements OnInit {

  client:Client ={
    name: '',
    cpf: '',
    email: '',
    password: '',
    profile: [],
    dateCreation: ''
  }

  constructor(private clientService:ClientService,
              private toast:ToastrService,
              private router:Router,
              private activatedRouter:ActivatedRoute) { }

  ngOnInit(): void {
    this.client.personId = this.activatedRouter.snapshot.paramMap.get('id');
    this.findById();
  }

  findById():void{
    this.clientService.findById(this.client.personId).subscribe(
      response =>{
        response.profile = [];
        this.client = response;
      }
    )
  }

  delete():void{
      this.clientService.delete(this.client.personId).subscribe(
        () => {
          this.toast.success('Cliente deletado com sucesso','Deletado com sucesso')
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

}
