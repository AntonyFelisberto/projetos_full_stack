import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path:'',pathMatch:'full',redirectTo:'cousers',  //PATHMATCH pra garantir que a url esta correta e fazer o redirect E REDIRECT TO redireciona pro modulo
  },
  {
    path:'cousers',
    loadChildren: () => import('./courses/courses.module').then(m => m.CoursesModule) //quando o path for courses carregue de forma altomatica o modulo
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
