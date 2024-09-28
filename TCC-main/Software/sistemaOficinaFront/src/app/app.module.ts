import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {SidebarModule} from 'ng-sidebar'

import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { HttpClientModule } from '@angular/common/http';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CardModule} from 'primeng/card';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {InputSwitchModule} from 'primeng/inputswitch';
import { FormsModule } from '@angular/forms';
import { OverlayModule } from '@angular/cdk/overlay'
import { CdkMenuModule } from '@angular/cdk/menu'
import {InputMaskModule} from 'primeng/inputmask';

import {PasswordModule} from 'primeng/password';
import { CalendarModule } from 'primeng/calendar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatRadioModule } from '@angular/material/radio';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';

import {InputNumberModule} from 'primeng/inputnumber';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {InputTextareaModule} from 'primeng/inputtextarea';

import { CadastroMateriaisComponent } from './views/cadastro-materiais/cadastro-materiais.component';
import { VisualizacaoMateriaisComponent } from './views/visualizacao-materiais/visualizacao-materiais.component';
import { CadastroUsuarioComponent } from './views/cadastro-usuario/cadastro-usuario.component';
import { ReenvioOrcamentoComponent } from './views/reenvio-orcamento/reenvio-orcamento.component';
import { EnviarOrcamentoComponent } from './views/enviar-orcamento/enviar-orcamento.component';
import { VisualizarOrcamentoComponent } from './views/visualizar-orcamento/visualizar-orcamento.component';
import { CronogramaComponent } from './views/cronograma/cronograma.component';
import { LoginComponent } from './views/login/login.component';
import { InicioComponent } from './views/inicio/inicio.component';
import { CommonModule } from '@angular/common';

import {PickListModule} from 'primeng/picklist';
import { DragDropModule } from 'primeng/dragdrop';
import { PanelModule } from 'primeng/panel';
import { TableModule } from 'primeng/table';
import { TabViewModule } from 'primeng/tabview';
import { ProvedorDeDadosInterceptor } from './services/interceptor/interceptar.interceptor';
import { NavComponent } from './views/nav/nav.component';

import {ToastModule} from 'primeng/toast';
import { NgxMaskModule } from 'ngx-mask';

import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    CadastroMateriaisComponent,
    VisualizacaoMateriaisComponent,
    CadastroUsuarioComponent,
    ReenvioOrcamentoComponent,
    EnviarOrcamentoComponent,
    VisualizarOrcamentoComponent,
    CronogramaComponent,
    LoginComponent,
    InicioComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SidebarModule,
    MatIconModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatToolbarModule,
    MatSidenavModule,
    BrowserAnimationsModule,
    CardModule,
    CdkMenuModule,
    OverlayModule,
    InputTextModule,
    ButtonModule,
    CommonModule,
    InputSwitchModule,
    FormsModule,
    MatInputModule,
    PasswordModule,
    MatFormFieldModule,
    MatPaginatorModule,
    MatSnackBarModule,
    MatCheckboxModule,
    MatButtonModule,
    MatSelectModule,
    MatTableModule,
    MatRadioModule,
    MatListModule,
    MatCardModule,
    CalendarModule,
    InputNumberModule,
    InputMaskModule,
    TableModule,
    TabViewModule,
    PanelModule,
    DragDropModule,
    PickListModule,
    BrowserModule,
    InputTextareaModule,
    BrowserAnimationsModule,
    ToastModule,
    NgxMaskModule.forRoot(),
  ],
  providers: [ProvedorDeDadosInterceptor],
  bootstrap: [AppComponent]
})
export class AppModule {}