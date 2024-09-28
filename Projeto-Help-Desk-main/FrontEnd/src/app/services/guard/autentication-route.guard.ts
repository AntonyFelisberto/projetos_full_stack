import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import { AuthenticateService } from '../authenticate/authenticate.service';

@Injectable({
  providedIn: 'root'
})
export class AutenticationRouteGuard implements CanActivate {

  constructor(private authService:AuthenticateService,private router:Router){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean{
    let authenticated = this.authService.isAuthenticated();

    if(authenticated){
      return true;
    }else{
      this.router.navigate(['login']);
      return false;
    }
  }
  
}
