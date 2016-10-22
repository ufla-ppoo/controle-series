package br.ufla.dcc.ppoo.modelo;

import java.util.ArrayList;

public class Serie {
    
    private String titulo;
    private String genero;
    private String autor;
    private String anoLancamento;
    private String numeroDeTemporadas;
    private ArrayList<String> elenco;

    public Serie(String titulo, String genero, String anoLancamento, String numeroDeTemporadas) {
        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.numeroDeTemporadas = numeroDeTemporadas;
    }


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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNumeroDeTemporadas() {
        return numeroDeTemporadas;
    }

    public void setNumeroDeTemporadas(String numeroDeTemporadas) {
        this.numeroDeTemporadas = numeroDeTemporadas;
    }

    public ArrayList<String> getElenco() {
        return elenco;
    }

    public void setElenco(ArrayList<String> elenco) {
        this.elenco = elenco;
    }

    
}
