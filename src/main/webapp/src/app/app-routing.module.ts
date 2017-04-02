import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { GoleadoresComponent } from './goleadores/goleadores.component';
import { JogadoresComponent } from './jogadores/jogadores.component';
import { PartidasComponent } from './partidas/partidas.component';

const routes: Routes = [
  {
    path: 'goleadores',
    component: GoleadoresComponent
  },
  {
    path: 'jogadores',
    component: JogadoresComponent
  },
  {
    path: 'partidas',
    component: PartidasComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
