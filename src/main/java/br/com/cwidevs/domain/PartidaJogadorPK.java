package br.com.cwidevs.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author murillo.goulart
 */
@Embeddable
public class PartidaJogadorPK implements Serializable {
   
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "partida_id")
    private Partida partida;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.partida);
        hash = 53 * hash + Objects.hashCode(this.jogador);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PartidaJogadorPK other = (PartidaJogadorPK) obj;
        if (!Objects.equals(this.partida, other.partida)) {
            return false;
        }
        if (!Objects.equals(this.jogador, other.jogador)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "PartidaJogadorPK{" + "partida=" + partida.getId() + ", jogador=" + jogador.getId() + '}';
    }
    
}
