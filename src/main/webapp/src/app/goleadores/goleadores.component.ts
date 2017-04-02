import { Component, OnInit } from '@angular/core';

import { Goleador } from './shared/goleador.model';
import { GoleadorService } from './shared/goleador.service';
import { JogadorService } from '../jogadores/shared/jogador.service';

@Component({
  selector: 'gol-goleadores',
  templateUrl: './goleadores.component.html',
  styleUrls: ['./goleadores.component.css']
})
export class GoleadoresComponent implements OnInit {
  goleadores: Goleador[];

  constructor(private goleadorService: GoleadorService,
              private jogadorService: JogadorService) {

  }

  ngOnInit(): void {
    this.getGoleadores();
  }

  private getGoleadores(): void {
    this.goleadorService.getGoleadores().then(
      goleadores => this.goleadores = goleadores
    );
  }

  private getNome(id: number): string {
    return this.jogadorService.getJogador(id).nome;
  }
}
