import { environment } from './../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from 'src/app/models/Usuarios/Usuario';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UsuarioDTO } from 'src/app/models/Usuarios/UsuarioDTO';
import { Token } from 'src/app/models/Usuarios/Token';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  jwtService:JwtHelperService = new JwtHelperService();

  private linkParaConexaoBackEnd = environment.linkDeConexaoComBackEnd;

  constructor(private httpClient:HttpClient) { }

  public adicionarUsuarios(usuario:Usuario){
    return this.httpClient.post<Usuario>(`${this.linkParaConexaoBackEnd}/usuario/add`,usuario);
  }

  public logar(usuario:Usuario){
    return this.httpClient.post(`${this.linkParaConexaoBackEnd}/login`,usuario, {
      observe:'response',
      responseType:'text'
    });
  }

  public trazerIdUsuario(usuario:UsuarioDTO){
    return this.httpClient.post(`${this.linkParaConexaoBackEnd}/usuario/trazerIdUsuario`,usuario)
  }

  sucessFullLogin(authToken:string){
    Token.token = authToken;
  }

  isAuthenticated(){
    let token = Token.token;
    if(token != null){
      return !this.jwtService.isTokenExpired(token)
    }
    return false;
  }

  logout(){
    localStorage.clear();
  }

}
