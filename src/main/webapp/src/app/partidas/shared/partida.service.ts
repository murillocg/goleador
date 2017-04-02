import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Partida } from './partida.model';
import { PARTIDAS } from './mock-partidas';

@Injectable()
export class PartidaService {

  private partidasUrl = 'http://0.0.0.0:32768/partidas';

  constructor(private http: Http) {

  }

  getPartidas(): Promise<Partida[]> {
    return Promise.resolve(PARTIDAS);
  }

  getPartidasHttp(): Promise<Partida[]> {
    return this.http.get(this.partidasUrl)
      .toPromise()
      .then(response => response.json() as Partida[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
      console.error('An error occurred', error);
      return Promise.reject(error.message || error);
  }
}
