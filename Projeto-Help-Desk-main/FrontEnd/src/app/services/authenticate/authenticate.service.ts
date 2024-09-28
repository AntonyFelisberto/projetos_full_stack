import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { API_CONFIG } from 'src/app/config/api.config';
import { Credentials } from 'src/app/models/Credentials';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  jwtService:JwtHelperService = new JwtHelperService();

  constructor(private httpClient:HttpClient) { }

  autenticate(credentials:Credentials){
    return this.httpClient.post(`${API_CONFIG.baseUrl}/login`,credentials, {
      observe:'response',
      responseType:'text'
    });
  }

  sucessFullLogin(authToken:string){
    localStorage.setItem('token',authToken);
  }

  isAuthenticated(){
    let token = localStorage.getItem('token');
    if(token != null){
      return !this.jwtService.isTokenExpired(token)
    }
    return false;
  }

  logout(){
    localStorage.clear();
  }

}
