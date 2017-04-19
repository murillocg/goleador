import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


import { Jogador } from './jogador.model';

@Injectable()
export class JogadorService {

  private jogadoresUrl = '/api/jogadores';

  constructor(private http: Http) { }

  getJogadores(): Observable<Jogador[]> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.get(this.jogadoresUrl, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  getJogador(id: number): Observable<Jogador> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.get(this.jogadoresUrl + '/' + id.toString(), options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  addJogador(jogador: Jogador): Observable<Jogador[]> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.jogadoresUrl, jogador, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  updateJogador(jogador: Jogador): Observable<Jogador[]> {
    let url = this.jogadoresUrl + '/' + jogador.id.toString();
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(url, jogador, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  removeJogador(jogador: Jogador): Observable<Jogador[]> {
    let url = this.jogadoresUrl + '/' + jogador.id.toString();
    return this.http.delete(url)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private extractData(response: Response) {
    let body = response.json();
    return body;
  }

  private handleError(error: Response | any): Observable  <any> {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}
