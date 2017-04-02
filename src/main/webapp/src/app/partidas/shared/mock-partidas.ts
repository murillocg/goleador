import { Partida } from './partida.model';

export const PARTIDAS: Partida[] = [
  { id : 1, adversario : 'Grêmio', dataRealizacao : '20/08/2015', golsPro : 5, golsContra : 4, jogadoresGols : [ { jogadorId : 1, gols: 4 }, { jogadorId : 2, gols : 1 }]},
  { id : 2, adversario : 'Internacional', dataRealizacao : '21/08/2015', golsPro : 2, golsContra : 1, jogadoresGols : [ { jogadorId : 3, gols: 2 }]},
  { id : 3, adversario : 'Juventude', dataRealizacao : '22/08/2015', golsPro : 4, golsContra : 8, jogadoresGols : [ { jogadorId : 1, gols: 1 }, { jogadorId : 4, gols : 7 }]}
];
