import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from '@angular/router';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/catch';

import { Jogador } from '../jogadores/shared/jogador.model';
import { JogadorService } from '../jogadores/shared/jogador.service';

@Injectable()
export class JogadorFormResolver implements Resolve<Jogador> {

  constructor(private jogadorService: JogadorService, private router: Router) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Jogador> {
    let id = +route.params['id'];

    return this.jogadorService.getJogador(id).catch(
      err => {
        this.router.navigate(['/jogadores']);
        return Observable.of(null);
      }
    );
  }
}
