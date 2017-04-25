import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';

import { Partida } from './partida.model';

@Injectable()
export class PartidaService {

  private partidasUrl = '/api/partidas';

  constructor(private http: Http) { }

  getPartidas(): Observable<Partida[]> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.get(this.partidasUrl, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  getPartida(id: number): Observable<Partida> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.get(this.partidasUrl + '/' + id.toString(), options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  savePartida(partida: Partida): Observable<Partida[]> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.partidasUrl, partida, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  removePartida(partida: Partida): Observable<Partida[]> {
    let url = this.partidasUrl + '/' + partida.id.toString();
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
