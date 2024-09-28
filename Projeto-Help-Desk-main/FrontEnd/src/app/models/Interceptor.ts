import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { AuthInterceptor } from "../services/interceptor/auth-interceptor.interceptor";

export const AuthInterceptorProvider = [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ]