import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';


import { Jogador } from './jogador.model';

@Injectable()
export class JogadorService {

  private jogadoresUrl = '/api/jogadores';

  constructor(private http: Http) { }

  getJogadores(): Observable<Jogador[]> {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const options = new RequestOptions({ headers: headers });
    return this.http.get(this.jogadoresUrl, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  getJogador(id: number): Observable<Jogador> {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const options = new RequestOptions({ headers: headers });
    return this.http.get(this.jogadoresUrl + '/' + id.toString(), options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  saveJogador(jogador: Jogador): Observable<Jogador[]> {
    const headers = new Headers({ 'Content-Type': 'application/json' });
    const options = new RequestOptions({ headers: headers });
    return this.http.post(this.jogadoresUrl, jogador, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  removeJogador(jogador: Jogador): Observable<Jogador[]> {
    const url = this.jogadoresUrl + '/' + jogador.id.toString();
    return this.http.delete(url)
      .catch(this.handleError);
  }

  private extractData(response: Response) {
    const body = response.json();
    return body;
  }

  private handleError(error: Response | any): Observable  <any> {
    let errorMsg: string;
    if (!(error instanceof Response)) {
      errorMsg = error.message ? error.message : error.toString();
      console.error(errorMsg);
    } else if (error.status === 400) {
      const errorType = error.headers.get('x-goleador-error');
      switch (errorType) {
        case 'error.nameexists': {
          errorMsg = 'Já existe um jogador com esse nome cadastrado!';
          break;
        }
        case 'error.scored': {
          errorMsg = 'O jogador já marcou gol(s) pela equipe!';
          break;
        }
        default: {
          errorMsg = 'Erro não mapeado!';
        }
      }
    } else {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errorMsg = `${error.status} - ${error.statusText || ''} ${err}`;
      console.error(errorMsg);
    }
    return Observable.throw(errorMsg);
  }
}
