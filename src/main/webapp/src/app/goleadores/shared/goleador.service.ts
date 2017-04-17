import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { Goleador } from './goleador.model';

@Injectable()
export class GoleadorService {

  private goleadoresUrl = '/api/goleadores';

  constructor(private http: Http) { }

  getGoleadores(): Observable<Goleador[]> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.get(this.goleadoresUrl, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  getGoleador(jogador: number): Observable<Goleador> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.get(this.goleadoresUrl + '/' + jogador.toString(), options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  addGoleador(goleador: Goleador): Observable<Goleador[]> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.goleadoresUrl, goleador, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  updateGoleador(goleador: Goleador): Observable<Goleador[]> {
    let url = this.goleadoresUrl + '/' + goleador.jogador.toString();
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.put(url, goleador, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  removeGoleador(goleador: Goleador): Observable<Goleador[]> {
    let url = this.goleadoresUrl + '/' + goleador.jogador.toString();
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
