import { Injectable } from '@angular/core';

import { Jogador } from './jogador.model';

@Injectable()
export class JogadorFilterService {

  constructor() { }

  filter(data: string, properties: Array<string>, originalList: Jogador[]) {
    let filteredList: Jogador[];
    if (data && properties && originalList) {
      data = data.toLowerCase();
      let filtered = originalList.filter(
        item => {
          let match = false;
          for (let property of properties) {
            if (item[property].toString().toLowerCase().indexOf(data) > -1) {
              match = true;
              break;
            }
          };
          return match;
        }
      );
      filteredList = filtered;
    } else {
      filteredList = originalList;
    }
    return filteredList;
  }
}
