import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component';
import { GoleadoresComponent } from './goleadores/goleadores.component';
import { JogadoresComponent } from './jogadores/jogadores.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { PartidasComponent } from './partidas/partidas.component';

const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent
  },
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
  },
  {
    path: '',
    redirectTo: '/dashboard',
    pathMatch: 'full'
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
