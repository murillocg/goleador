package br.com.cwidevs.domain;

import org.springframework.data.annotation.Id;

/**
 *
 * @author murillo.goulart
 */
public class Jogador {

    @Id
    private Long id;

    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
