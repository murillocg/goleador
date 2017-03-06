package br.com.cwidevs.dto;

import br.com.cwidevs.domain.Jogador;

/**
 *
 * @author murillo.goulart
 */
public class Goleador {
    
    private Jogador jogador;
    
    private Long totalGols;

    public Goleador() {
    }

    public Goleador(Jogador jogador, Long totalGols) {
        this.jogador = jogador;
        this.totalGols = totalGols;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Long getTotalGols() {
        return totalGols;
    }

    public void setTotalGols(Long totalGols) {
        this.totalGols = totalGols;
    }
    
}
