export class Partida {
  id: number;
  adversario: string;
  dataRealizacao: string;
  golsPro: number;
  golsContra: number;
  jogadoresGols: JogadorGols[];
}

class JogadorGols {
  jogadorId: number;
  gols: number;
}
