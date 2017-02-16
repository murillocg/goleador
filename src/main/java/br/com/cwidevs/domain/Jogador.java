package br.com.cwidevs.domain;

import javax.validation.constraints.NotNull;

/**
 *
 * @author murillo.goulart
 */
public class Jogador {

    @NotNull
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}
