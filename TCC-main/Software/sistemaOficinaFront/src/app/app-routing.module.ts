import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EnviarOrcamentoComponent } from './views/enviar-orcamento/enviar-orcamento.component';
import { NavComponent } from './views/nav/nav.component';

import { CadastroMateriaisComponent } from './views/cadastro-materiais/cadastro-materiais.component';
import { InicioComponent } from './views/inicio/inicio.component';
import { LoginComponent } from './views/login/login.component';
import { VisualizacaoMateriaisComponent } from './views/visualizacao-materiais/visualizacao-materiais.component';
import { CronogramaComponent } from './views/cronograma/cronograma.component';
import { CadastroUsuarioComponent } from './views/cadastro-usuario/cadastro-usuario.component';
import { VisualizarOrcamentoComponent } from './views/visualizar-orcamento/visualizar-orcamento.component';
import { ReenvioOrcamentoComponent } from './views/reenvio-orcamento/reenvio-orcamento.component';
import { TokenGuard } from './services/guard/token.guard';

const routes: Routes = [
  { path:'',component: LoginComponent},
  {
    path: 'nav', component:NavComponent, canActivate:[TokenGuard],children: [
    { path:'inicio', component:InicioComponent},
    { path:'enviarOrcamento', component:EnviarOrcamentoComponent},
    { path:'reenviarOrcamento', component:ReenvioOrcamentoComponent},
    { path:'visualizarOrcamento', component:VisualizarOrcamentoComponent},
    { path:'cadastrarUsuario', component:CadastroUsuarioComponent},
    { path:'cronograma', component:CronogramaComponent},
    { path:'cadastrarMateriais', component:CadastroMateriaisComponent},
    { path:'visualizarMateriais', component:VisualizacaoMateriaisComponent},
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }