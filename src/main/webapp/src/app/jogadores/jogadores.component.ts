import { Component, OnInit } from '@angular/core';

import { Jogador } from './shared/jogador.model';
import { JogadorService } from './shared/jogador.service';

@Component({
  selector: 'gol-jogadores',
  templateUrl: './jogadores.component.html',
  styleUrls: ['./jogadores.component.css']
})
export class JogadoresComponent implements OnInit {
  jogadores: Jogador[];

  constructor(private jogadorService: JogadorService) {

  }

  ngOnInit(): void {
    this.getJogadores();
  }

  private getJogadores(): void {
    this.jogadorService.getJogadores().then(
    //this.jogadorService.getJogadoresHttp().then(
      jogadores => this.jogadores = jogadores
      //jogadores => console.log(jogadores)
    );
  }
}
