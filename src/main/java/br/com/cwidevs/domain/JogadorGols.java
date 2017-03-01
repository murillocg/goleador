package br.com.cwidevs.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author murillo.goulart
 */
@Entity
public class JogadorGols implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JogadorGols")
    @SequenceGenerator(name = "JogadorGols")
    private Long id;

    @NotNull
    @Column(name = "gols", nullable = false)
    private Integer gols;

    @ManyToOne
    private Partida partida;

    @ManyToOne
    private Jogador jogador;

    public JogadorGols() {
    }

    public JogadorGols(Integer gols, Partida partida, Jogador jogador) {
        this.gols = gols;
        this.partida = partida;
        this.jogador = jogador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGols() {
        return gols;
    }

    public void setGols(Integer gols) {
        this.gols = gols;
    }

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
    
}
