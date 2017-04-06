import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { DashboardComponent } from './dashboard/dashboard.component';
import { GoleadoresComponent } from './goleadores/goleadores.component';
import { GoleadorService } from './goleadores/shared/goleador.service';
import { JogadoresComponent } from './jogadores/jogadores.component';
import { JogadorFormComponent } from './jogador-form/jogador-form.component';
import { JogadorService } from './jogadores/shared/jogador.service';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { PartidasComponent } from './partidas/partidas.component';
import { PartidaService } from './partidas/shared/partida.service';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    GoleadoresComponent,
    JogadoresComponent,
    JogadorFormComponent,
    PageNotFoundComponent,
    PartidasComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    ReactiveFormsModule,
    AppRoutingModule,
    NgbModule.forRoot()
  ],
  providers: [
    GoleadorService,
    JogadorService,
    PartidaService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
