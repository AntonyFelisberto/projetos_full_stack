import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Token } from 'src/app/models/Usuarios/Token';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = Token.token
    if(token){
      const clonarRequest = request.clone({headers:request.headers.set('Authorization',`Bearer ${token}`)})
      return next.handle(clonarRequest)
    }else{
      return next.handle(request);
    }
  }

}

export const ProvedorDeDadosInterceptor = [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: Interceptor,
    multi: true
  }
]