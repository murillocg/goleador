import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { JogadoresComponent } from './jogadores/jogadores.component';

const routes: Routes = [
  {
    path: 'jogadores',
    component: JogadoresComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
