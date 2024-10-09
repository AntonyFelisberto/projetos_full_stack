//O IDEAL E AS CONVENSÕES É SEMPRE DEIXAR OS IMPORTS ANGULAR NO INICIO
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

//O IDEAL E AS CONVENSÕES É SEMPRE DEIXAR OS MODULOS NO MEIO EM ORDEM ALFABETICA
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
/**
 * TODOS OS COMPONENTES E DEPENDENCIAS FICAM AQUI
 */
//O IDEAL E AS CONVENSÕES É SEMPRE DEIXAR AS DEPENCIAS IMPORTADAS NO FIM EM ORDEM ALFABETICA
//ABAIXO UMA DEPENCIA DO ANGULAR MATERIAL APÓS INSTALA LO
import {MatToolbarModule} from '@angular/material/toolbar';

@NgModule({
  declarations: [
    //COMPONENTS NOVOS
    AppComponent
  ],
  imports: [
    //NOVOS IMPORTS E DEPENDENCIAS
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
