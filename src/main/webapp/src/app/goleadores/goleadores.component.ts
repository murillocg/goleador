import { Component, OnInit } from '@angular/core';

import { Goleador } from './shared/goleador.model';
import { GoleadorService } from './shared/goleador.service';
import { Jogador } from '../jogadores/shared/jogador.model';
import { JogadorService } from '../jogadores/shared/jogador.service';

@Component({
  selector: 'gol-goleadores',
  templateUrl: './goleadores.component.html',
  styleUrls: ['./goleadores.component.css']
})
export class GoleadoresComponent implements OnInit {

  goleadores: Goleador[];
  jogadores: Jogador[];

  constructor(private goleadorService: GoleadorService, private jogadorService: JogadorService) {}

  ngOnInit(): void {
    this.getGoleadores();
    this.getJogadores();
}

  private getGoleadores(): void {
    this.goleadorService.getGoleadores().subscribe(
      goleadores => this.goleadores = goleadores
    )
  }

  private getJogadores(): void {
    this.jogadorService.getJogadores().subscribe(
      partidas => this.jogadores = partidas
    );
  }

  private getNome(goleador: Goleador): string {
    let jogador = this.jogadores.find(jogador => jogador.id === goleador.jogador);
    return jogador.nome;
  }
}
