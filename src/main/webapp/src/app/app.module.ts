import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { JogadoresComponent } from './jogadores/jogadores.component';
import { JogadorService } from './jogadores/shared/jogador.service';


@NgModule({
  declarations: [
    AppComponent,
    JogadoresComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [JogadorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
