import { JogadorGols } from './jogador-gols.model';

export class Partida {
  id: number;
  adversario: string;
  dataRealizacao: string;
  golsPro: number;
  golsContra: number;
  jogadoresGols: JogadorGols[];
}
