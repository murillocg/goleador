import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { DashboardComponent } from './dashboard/dashboard.component';
import { GoleadoresComponent } from './goleadores/goleadores.component';
import { GoleadorService } from './goleadores/shared/goleador.service';
import { JogadoresComponent } from './jogadores/jogadores.component';
import { JogadorService } from './jogadores/shared/jogador.service';
import { PartidasComponent } from './partidas/partidas.component';
import { PartidaService } from './partidas/shared/partida.service';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    GoleadoresComponent,
    JogadoresComponent,
    PartidasComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [
    GoleadorService,
    JogadorService,
    PartidaService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
