package br.com.cwidevs.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author murillo.goulart
 */
@Entity
@Table(name = "partida_jogador")
public class PartidaJogador implements Serializable {

    @EmbeddedId
    private PartidaJogadorPK id;

    @NotNull
    @Column(name = "gols", nullable = false)
    private Integer gols;

    public PartidaJogadorPK getId() {
        return id;
    }

    public void setId(PartidaJogadorPK id) {
        this.id = id;
    }

    public Integer getGols() {
        return gols;
    }

    public void setGols(Integer gols) {
        this.gols = gols;
    }

        
}
