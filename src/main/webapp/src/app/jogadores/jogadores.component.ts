import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

import { Jogador } from './shared/jogador.model';
import { JogadorFilterService } from './shared/jogador-filter.service';
import { JogadorService } from './shared/jogador.service';

@Component({
  selector: 'gol-jogadores',
  templateUrl: './jogadores.component.html',
  styleUrls: ['./jogadores.component.css']
})
export class JogadoresComponent implements OnInit {

  filteredJogadores: Jogador[];
  jogadores: Jogador[];

  constructor(private jogadorService: JogadorService,
              private jogadorFilterService: JogadorFilterService,
              private router: Router) { }

  filterChanged(filter: string): void {
    this.filteredJogadores = this.jogadorFilterService.filter(filter, ['nome'], this.jogadores);
  }

  editJogador(jogador: Jogador): void {
    this.router.navigate(['/jogador', jogador.id]);
  }

  removeJogador(jogador: Jogador): void {
    this.jogadorService.removeJogador(jogador).subscribe(
      jogadores => console.log(jogadores)
    );
  }

  ngOnInit(): void {
    this.getJogadores();
  }

  private getJogadores(): void {
    this.jogadorService.getJogadores().subscribe(
      jogadores => {
        this.jogadores = jogadores;
        this.filteredJogadores = jogadores;
      }
    );
  }
}
