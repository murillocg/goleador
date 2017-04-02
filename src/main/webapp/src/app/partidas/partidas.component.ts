import { Component, OnInit } from '@angular/core';

import { Partida } from './shared/partida.model';
import { PartidaService } from './shared/partida.service';

import { JogadorService } from '../jogadores/shared/jogador.service';

@Component({
  selector: 'gol-partidas',
  templateUrl: './partidas.component.html',
  styleUrls: ['./partidas.component.css']
})
export class PartidasComponent implements OnInit {
  partidas: Partida[];

  constructor(private partidaService: PartidaService,
              private jogadorService: JogadorService) {

  }

  ngOnInit(): void {
    this.getPartidas();
  }

  private getPartidas(): void {
    this.partidaService.getPartidas().then(
      partidas => this.partidas = partidas
    );
  }

  private getNome(id: number): string {
    return this.jogadorService.getJogador(id).nome;
  }
}
