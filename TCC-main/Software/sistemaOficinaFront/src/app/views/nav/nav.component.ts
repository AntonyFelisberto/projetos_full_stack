import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Token } from 'src/app/models/Usuarios/Token';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
    this.router.navigate(['nav/inicio'])
  }

  limparToken(){
    Token.token = ''
  }

}