import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Jogador } from './jogador.model';
import { JOGADORES } from './mock-jogadores';

@Injectable()
export class JogadorService {

  private jogadores: Jogador[];
  private jogadoresUrl = 'http://0.0.0.0:32768/jogadores';
  private nextId: number;

  constructor(private http: Http) {
    this.init();
  }

  getJogadores(): Jogador[] {
    return this.jogadores;
  }

  getJogadoresHttp(): Promise<Jogador[]> {
    return this.http.get(this.jogadoresUrl)
      .toPromise()
      .then(response => response.json() as Jogador[])
      .catch(this.handleError);
  }

  getJogador(id: number): Jogador {
    return this.jogadores.find(jogador => jogador.id === +id);
  }

  addJogador(jogador: Jogador): void {
    this.jogadores.push({
      id: this.nextId++,
      nome: jogador.nome,
      ativo: true
    });
  }

  updateJogador(jogador: Jogador): void {
    const index = this.jogadores.indexOf(jogador);
    this.jogadores[index].nome = jogador.nome;
  }

  removeJogador(jogador: Jogador): void {
    const index = this.jogadores.indexOf(jogador);
    this.jogadores.splice(index, 1);
  }

  private handleError(error: any): Promise<any> {
      console.error('An error occurred', error);
      return Promise.reject(error.message || error);
  }

  private init() {
    this.jogadores = JOGADORES;
    this.nextId = 100;
  }
}
