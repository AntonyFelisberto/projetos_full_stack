import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import { UsuarioService } from '../usuarioService/usuario.service';

@Injectable({
  providedIn: 'root'
})
export class TokenGuard implements CanActivate {

  constructor(private usuarioService:UsuarioService,
              private router:Router){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot):boolean{
      let isAuthenticated = this.usuarioService.isAuthenticated();
      if(isAuthenticated){
        return true;
      }else{
        this.router.navigate(['/']);
        return false;
      }
  }

}
