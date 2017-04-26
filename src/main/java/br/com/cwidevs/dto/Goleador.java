package br.com.cwidevs.dto;

/**
 *
 * @author murillo.goulart
 */
public class Goleador {
    
    private Long jogador;
    
    private Long totalGols;

    public Goleador() {
    }

    public Goleador(Long jogador, Long totalGols) {
        this.jogador = jogador;
        this.totalGols = totalGols;
    }

    public Long getJogador() {
        return jogador;
    }

    public void setJogador(Long jogador) {
        this.jogador = jogador;
    }

    public Long getTotalGols() {
        return totalGols;
    }

    public void setTotalGols(Long totalGols) {
        this.totalGols = totalGols;
    }

    @Override
    public String toString() {
        return "Goleador{" + "jogador=" + jogador + ", totalGols=" + totalGols + '}';
    }
    
}
