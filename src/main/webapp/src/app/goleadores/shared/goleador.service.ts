import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Goleador } from './goleador.model';
import { GOLEADORES } from './mock-goleadores';

@Injectable()
export class GoleadorService {

  private goleadoresUrl = 'http://0.0.0.0:32768/goleadores';

  constructor(private http: Http) {

  }

  getGoleadores(): Promise<Goleador[]> {
    return Promise.resolve(GOLEADORES);
  }

  getGoleadoresHttp(): Promise<Goleador[]> {
    return this.http.get(this.goleadoresUrl)
      .toPromise()
      .then(response => response.json() as Goleador[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
      console.error('An error occurred', error);
      return Promise.reject(error.message || error);
  }
}
