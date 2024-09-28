import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

//PARA TRABALHAR COM FORMULARIOS
import { FormsModule , ReactiveFormsModule } from '@angular/forms';

//PARA FAZER REQUISIÇÕES HTTP
import {HttpClientModule} from '@angular/common/http'

//ANGULAR MATERIAL
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatPaginatorModule} from '@angular/material/paginator'
import {MatSnackBarModule} from '@angular/material/snack-bar'
import {MatCheckboxModule} from '@angular/material/checkbox'
import {MatToolbarModule} from '@angular/material/toolbar'
import {MatSidenavModule} from '@angular/material/sidenav'
import {MatButtonModule} from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select'
import {MatTableModule} from '@angular/material/table'
import {MatRadioModule} from '@angular/material/radio'
import {MatInputModule} from '@angular/material/input'
import {MatIconModule} from '@angular/material/icon'
import {MatListModule} from '@angular/material/list'
import {MatCardModule} from '@angular/material/card';

import { ToastrModule } from 'ngx-toastr';
import { NgxMaskModule } from 'ngx-mask';

import { NavComponent } from './components/nav/nav.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { TechnicalComponent } from './components/tecnico/technical-list/technical.component';
import { LoginComponent } from './components/login/login.component'
import { AuthInterceptorProvider } from './models/Interceptor';
import { TecnicoCreateComponent } from './components/tecnico/tecnico-create/tecnico-create.component';
import { TecnicoUpdateComponent } from './components/tecnico/tecnico-update/tecnico-update.component';
import { TecnicoDeleteComponent } from './components/tecnico/tecnico-delete/tecnico-delete.component';
import { ClientListComponent } from './components/client/client-list/client-list.component';
import { ClientCreateComponent } from './components/client/client-create/client-create.component';
import { ClientUpdateComponent } from './components/client/client-update/client-update.component';
import { ClientDeleteComponent } from './components/client/client-delete/client-delete.component';
import { ChamadoListComponent } from './components/chamado/chamado-list/chamado-list.component';
import { ChamadoCreateComponent } from './components/chamado/chamado-create/chamado-create.component';
import { ChamadoUpdateComponent } from './components/chamado/chamado-update/chamado-update.component';
import { ChamadoReadComponent } from './components/chamado/chamado-read/chamado-read.component';
@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    HomeComponent,
    HeaderComponent,
    TechnicalComponent,
    LoginComponent,
    TecnicoCreateComponent,
    TecnicoUpdateComponent,
    TecnicoDeleteComponent,
    ClientListComponent,
    ClientCreateComponent,
    ClientUpdateComponent,
    ClientDeleteComponent,
    ChamadoListComponent,
    ChamadoCreateComponent,
    ChamadoUpdateComponent,
    ChamadoReadComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatPaginatorModule,
    MatSnackBarModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatSidenavModule,
    MatSelectModule,
    MatTableModule,
    MatRadioModule,
    MatInputModule,
    MatIconModule,
    MatListModule,
    MatCardModule,
    NgxMaskModule.forRoot(),
    ToastrModule.forRoot({
      timeOut:4000,
      closeButton: true,
      progressBar:true
    })
  ],
  providers: [AuthInterceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
