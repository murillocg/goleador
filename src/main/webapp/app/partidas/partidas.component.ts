import { Component, OnInit } from '@angular/core';

import { JogadorGols } from './shared/jogador-gols.model';
import { Partida } from './shared/partida.model';
import { PartidaService } from './shared/partida.service';

import { Jogador } from '../jogadores/shared/jogador.model';
import { JogadorService } from '../jogadores/shared/jogador.service';

@Component({
  selector: 'gol-partidas',
  templateUrl: './partidas.component.html',
  styleUrls: ['./partidas.component.css']
})
export class PartidasComponent implements OnInit {

  partidas: Partida[];
  private jogadores: Jogador[];

  constructor(private jogadorService: JogadorService,
              private partidaService: PartidaService) { }

  ngOnInit(): void {
    this.getJogadores();
    this.getPartidas();
  }

  private getPartidas(): void {
    this.partidaService.getPartidas().subscribe(
      partidas => this.partidas = partidas
    );
  }

  private getJogadores(): void {
    this.jogadorService.getJogadores().subscribe(
      jogadores => this.jogadores = jogadores
    );
  }

  private getNome(jogadorGols: JogadorGols): string {
    let jogador = this.jogadores.find(jogador => jogador.id === jogadorGols.jogadorId);
    return jogador.nome;
  }
}
