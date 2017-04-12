import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Jogador } from './jogador.model';
import { JOGADORES } from './mock-jogadores';

@Injectable()
export class JogadorService {

  private jogadores: Jogador[];
  private jogadoresUrl = 'http://0.0.0.0:32768/jogadores';

  constructor(private http: Http) {
    this.init();
  }

  getJogadores(): Promise<Jogador[]> {
    return Promise.resolve(JOGADORES);
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

  private handleError(error: any): Promise<any> {
      console.error('An error occurred', error);
      return Promise.reject(error.message || error);
  }

  private init() {
    this.getJogadores().then(
      jogadores => this.jogadores = jogadores
    );
  }
}
