import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoursesRoutingModule } from './courses-routing.module';
import { CoursesComponent } from './courses/courses.component';

/**
 * MODULO É COMO VOCE ORGANIZA DE FORMA LÓGICA SUA APLICAÇÃO
 * TODOS OS COMPONENTS GERADOS E CRIADOS DENTRO DO MODULO SÃO VISIVEIS SOMENTE DENTRO DO MODULO
 * CASO QUEIRA USALO EM OUTRO MODULO PRECISA EXPORTAR O COMPONENT
 */
@NgModule({
  declarations: [
    CoursesComponent
  ],
  imports: [
    CommonModule,
    CoursesRoutingModule
  ]
})
export class CoursesModule { }
