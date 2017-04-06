import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

import { Jogador } from './shared/jogador.model';
import { JogadorService } from './shared/jogador.service';

@Component({
  selector: 'gol-jogadores',
  templateUrl: './jogadores.component.html',
  styleUrls: ['./jogadores.component.css']
})
export class JogadoresComponent implements OnInit {
  jogadores: Jogador[];

  constructor(private jogadorService: JogadorService, private router: Router) {

  }

  editJogador(jogador: Jogador): void {
    this.router.navigate(['/jogador', jogador.id]);
  }

  removeJogador(jogador: Jogador): void {
    this.jogadorService.removeJogador(jogador);
  }

  ngOnInit(): void {
    this.getJogadores();
  }

  private getJogadores(): void {
    this.jogadores = this.jogadorService.getJogadores();
  }
}
