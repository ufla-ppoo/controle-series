package br.ufla.dcc.ppoo.modelo;

import java.util.ArrayList;

/**
 * Representa uma série do sistema.
 * 
 * @author Breno e Lucas
 */

public class Serie {
    
    private String titulo;
    private String genero;
    private String anoLancamento;
    private String numeroDeTemporadas;
    private String elenco;

    public Serie(String titulo, String genero, String anoLancamento, String numeroDeTemporadas,String elenco) {
        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.numeroDeTemporadas = numeroDeTemporadas;
        this.elenco=elenco;
    }

    
    
    // Métodos Getters e Setters
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public String getNumeroDeTemporadas() {
        return numeroDeTemporadas;
    }

    public void setNumeroDeTemporadas(String numeroDeTemporadas) {
        this.numeroDeTemporadas = numeroDeTemporadas;
    }

    public String getElenco() {
        return elenco;
    }

    public void setElenco(String elenco) {
        this.elenco = elenco;
    }

    
}
