import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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
  serverError: boolean;
  errorMessage: string;

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
      () => this.removeJogadorLocally(jogador),
      error =>  this.openAlert(error)
    );
  }

  closeAlert(): void {
    this.serverError = false;
    this.errorMessage = '';
  }

  ngOnInit(): void {
    this.closeAlert();
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

  private removeJogadorLocally(jogador: Jogador): void {
    const indexOriginal = this.jogadores.indexOf(jogador);
    const indexFiltered = this.filteredJogadores.indexOf(jogador);
    this.jogadores.splice(indexOriginal, 1);
    this.filteredJogadores.splice(indexFiltered, 1);
  }

  private openAlert(errorMessage: string): void {
    this.serverError = true;
    this.errorMessage = errorMessage;
  }
}
