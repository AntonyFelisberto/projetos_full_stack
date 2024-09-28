import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Credentials } from 'src/app/models/Credentials';
import { AuthenticateService } from 'src/app/services/authenticate/authenticate.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials:Credentials = {
    email:'',
    password:''
  };

  email = new FormControl(null, Validators.email)
  password = new FormControl(null, Validators.minLength(3))

  constructor(private toast: ToastrService,
              private loginService:AuthenticateService,
              private router:Router) { }

  ngOnInit(): void {
  }

  logar(){
    this.loginService.autenticate(this.credentials).subscribe( response =>{
      //PEGA O TOKEN DA RESPOSTA E TIRA O BEARER+ESPAÇAMENTO
      this.loginService.sucessFullLogin(response.headers.get('Authorization').substring(7));
      this.router.navigate([''])
    },() =>{
      this.toast.error('Usuario e/ou senha invalidos');
    })
  }

  validation():boolean{
    return this.email.valid && this.password.valid
  }

}
